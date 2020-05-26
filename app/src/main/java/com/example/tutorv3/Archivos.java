package com.example.tutorv3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorv3.ClasesAdmin.ClsArchivos;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Archivos extends AppCompatActivity {


    EditText etnombreclase,etdescripcionclase,etlinkyoutube;
    TextView etrutaarchivo,tvextension,tvruta,nombre,peso;
    Button btnabrregaleria,btnsubir;
    ImageView imgfoto;
    ProgressDialog progressDialog;

    private final int frames = 9;
    private int currentAnimationFrame = 0;
    private DatabaseReference referenceclaseprofesor;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    String user_id;
    private final int MIS_PERMISOS = 100;
    private static final int COD_SELECCIONA = 10;
    private static final int COD_ARCHIVO=15;
    String tipoarchivo;

    Uri uri;

    Uri pdfurl;
    String tipodocumento,nombrearchivo;
    String idarvhivo;


    android.app.AlertDialog.Builder builder1;
    AlertDialog alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivos);
        etrutaarchivo=(TextView)findViewById(R.id.txtrutaarchivo);
        btnabrregaleria=(Button)findViewById(R.id.idbtnabrirgaleria);
        tvextension=(TextView)findViewById(R.id.txtextension);
        tvruta=(TextView)findViewById(R.id.ruta);
        nombre=(TextView)findViewById(R.id.nombre);
        peso=(TextView)findViewById(R.id.peso);
        btnsubir=(Button)findViewById(R.id.btnsubir);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        referenceclaseprofesor=  FirebaseDatabase.getInstance().getReference("Archivos");
        if(solicitaPermisosVersionesSuperiores()){

        }

        btnabrregaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvextension.setText("");
               selectPdf();
              //  browseDocuments();
            }
        });

        btnsubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubirArchiviStorage("tipo",pdfurl);
            }
        });
    }
    private void selectPdf() {

        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
      //  intent.setType("*/*");
        startActivityForResult(intent,86);

    }
    private void browseDocuments(){

        String[] mimeTypes =
                {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                        "application/vnd.ms-PowerPoint","application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
                        "application/vnd.ms-Excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                        "text/plain",
                        "application/pdf",
                        "application/Zip"};

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
            if (mimeTypes.length > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
        } else {
            String mimeTypesStr = "";
            for (String mimeType : mimeTypes) {
                mimeTypesStr += mimeType + "|";
            }
            intent.setType(mimeTypesStr.substring(0,mimeTypesStr.length() - 1));
        }
        startActivityForResult(Intent.createChooser(intent,"ChooseFile"), 86);

    }
    private void SubirArchiviStorage(final String tipo, Uri pdfurl){


        if (pdfurl==null){

            String mensaje="falta adjuntar el archivo de tarea";
            String estado="fail";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }

        else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Subiendo..");
            progressDialog.setProgress(0);
            progressDialog.show();
            progressDialog.setCancelable(false);

            final StorageReference mountainsRef=mStorageRef.child("coreo").child(nombrearchivo);
            //    final StorageReference storageReference =storage.getReference();  // return root path
            final UploadTask uploadTask = mountainsRef.putFile(pdfurl);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return mountainsRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {

                                Uri dowloand=task.getResult();
                              String id  = referenceclaseprofesor.push().getKey();
                              ClsArchivos obj= new ClsArchivos(id,dowloand.toString(),"clase 2","fecha","file","1",nombrearchivo);
                             referenceclaseprofesor.child(id).setValue(obj);

                            } else {
                                //  Toast.makeText(EditarPerfil.this, "Error al subir", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Archivos.this, "Error " +e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    //track the progress of = our upload
                    int currentprogress= (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setProgress(currentprogress);
                    if (currentprogress==100){
                        progressDialog.dismiss();

                        String mensaje="Agregado Tarea";
                        String estadome="ok";
                        Toast.makeText(Archivos.this, mensaje, Toast.LENGTH_SHORT).show();

                    }


                }
            });


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        try {
            //para archivos
            if (requestCode==86 && resultCode==RESULT_OK && data!=null){
                //  Toast.makeText(this, "ppt", Toast.LENGTH_SHORT).show();

                pdfurl=data.getData(); //return the uir of selected file
                etrutaarchivo.setText(data.getData().getLastPathSegment());
                //   String rutaarchivo=data.getData().getLastPathSegment();
                tipoarchivo="file";
                tipodocumento="file";
                idarvhivo =data.getData().getLastPathSegment();
                String extenios=getExtension(data.getData().getLastPathSegment());
                tvruta.setText(data.toString());
                String mimeType = getContentResolver().getType(pdfurl);


                Cursor returnCursor = getContentResolver().query(pdfurl, null, null, null, null);
                tvextension.setText(mimeType);


                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                returnCursor.moveToFirst();

                nombre.setText(returnCursor.getString(nameIndex));
                nombrearchivo=returnCursor.getString(nameIndex);
                peso.setText(Long.toString(returnCursor.getLong(sizeIndex)));

            }
            // para imagenes

           /* else   if (requestCode==20 && resultCode==RESULT_OK && data!=null){
                Log.e("archivi ",data.getData().toString());
                uri=data.getData();
                idarvhivo =data.getData().getLastPathSegment();

                etrutaarchivo.setText(data.getData().getLastPathSegment());  // esto es importante para borrar archivo de storage
                String rutaarchivo=data.getData().getLastPathSegment();
                String r= getExtension(rutaarchivo);
                imgfoto.setImageURI(uri);
                tipodocumento = "img";
                tipoarchivo="img";

            }

            */

            else{
                Toast.makeText(this, "No seleciono un archivo", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(this, "Error "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private boolean solicitaPermisosVersionesSuperiores() {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){//validamos si estamos en android menor a 6 para no buscar los permisos
            return true;
        }

        //validamos si los permisos ya fueron aceptados
        if((getBaseContext().checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)&&getBaseContext().checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED){
            return true;
        }

        if ((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)||(shouldShowRequestPermissionRationale(CAMERA)))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MIS_PERMISOS);
        }

        return false;//implementamos el que procesa el evento dependiendo de lo que se defina aqui
    }
    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(getBaseContext());
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe conceder los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
            }
        });
        dialogo.show();
    }

    public static String getExtension(String filename) {
        //TODO : ESTO ES PARA SACAR LA EXTENSION DEL TIPO DE ARCHIVO
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }

    public static String getExtension3(String filename) {
        //TODO : ESTO ES PARA SACAR LA EXTENSION DEL TIPO DE ARCHIVO
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }
    public static String getExtension2(String filename) {
        int index = filename.lastIndexOf('/');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }
}
