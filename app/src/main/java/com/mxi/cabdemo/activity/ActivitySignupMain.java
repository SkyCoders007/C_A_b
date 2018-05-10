package com.mxi.cabdemo.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mxi.cabdemo.CommanClass;
import com.mxi.cabdemo.R;
import com.mxi.cabdemo.permission.PermissionUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by mxicoders on 5/6/17.
 */

public class ActivitySignupMain extends AppCompatActivity {


    //ImagePick
    public static final String FILE_NAME = "temp.jpg";
    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;
    private static final String TAG = ActivitySignupMain.class.getSimpleName();


    ImageView upload_img, upload_profile_pic,upload_success,upload_success2;
    LinearLayout upload_profile;
    RelativeLayout personalHead,carHead,rateHead;
    RelativeLayout first_done, first_inactive,second_inactive,third_inactive, second_done,third_done;
    RelativeLayout layout_next;
    RelativeLayout layout_header_step_first, layout_header_step_secound,layout_header_step_third;
    View register_step_one, register_step_two, register_step_third;
    EditText expiryDate, uploadLicense,uploadCarpic;
    Spinner city, state, country, car;

    LinearLayout rateLinearBottom;

    TextView txtName;

    File photo;

    Uri imageToUploadUri;

    CommanClass cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_signup_main);

        cc = new CommanClass(this);

        cc.loadPrefString("Saved Locale");

        init();

    }

    private void init() {

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                expiryDate.setText(sdf.format(myCalendar.getTime()));

            }

        };

        upload_success = (ImageView)findViewById(R.id.upload_sucess);
        upload_success2 = (ImageView)findViewById(R.id.upload_sucess2);

        rateLinearBottom = (LinearLayout)findViewById(R.id.rate_linear_bottom);

        personalHead = (RelativeLayout)findViewById(R.id.personalHead);
        carHead = (RelativeLayout)findViewById(R.id.carHeadClick);
        rateHead = (RelativeLayout)findViewById(R.id.ratesHeadClick);

        personalHead.setBackgroundColor(Color.parseColor("#4e4f51"));


        country = (Spinner) findViewById(R.id.spinner_city);
        state = (Spinner) findViewById(R.id.spinner_country);
        city = (Spinner) findViewById(R.id.spinner_state);
        car = (Spinner) findViewById(R.id.spinner_car_name);

        expiryDate = (EditText) findViewById(R.id.expiry_date);
        uploadLicense = (EditText) findViewById(R.id.edt_upload_license);
        uploadCarpic = (EditText) findViewById(R.id.edt_upload_car_pic);

        first_done = (RelativeLayout) findViewById(R.id.first_done);
        second_done = (RelativeLayout) findViewById(R.id.second_done);
        third_done = (RelativeLayout)findViewById(R.id.third_done);
        first_inactive = (RelativeLayout) findViewById(R.id.first_inactive);
        second_inactive = (RelativeLayout) findViewById(R.id.second_inactive);
        third_inactive = (RelativeLayout)findViewById(R.id.third_inactive);

        layout_header_step_first = (RelativeLayout) findViewById(R.id.ll_personal_details_active_step_first);
        layout_header_step_secound = (RelativeLayout) findViewById(R.id.ll_vehicle_details_inactive_step_first);
        layout_header_step_third = (RelativeLayout)findViewById(R.id.ll_rates_details_inactive_step_third);

        register_step_one = (View) findViewById(R.id.register_step_one);
        register_step_two = (View) findViewById(R.id.register_step_two);
        register_step_third = (View)findViewById(R.id.register_step_third);


        layout_next = (RelativeLayout) findViewById(R.id.layout_next);

        upload_profile_pic = (ImageView) findViewById(R.id.upload_profile);



        layout_header_step_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                register_step_two.setVisibility(View.GONE);

                register_step_third.setVisibility(View.GONE);

                register_step_one.setVisibility(View.VISIBLE);

                layout_next.setVisibility(View.GONE);

                personalHead.setBackgroundColor(Color.parseColor("#4e4f51"));
                carHead.setBackgroundColor(Color.parseColor("#2B3038"));
                rateHead.setBackgroundColor(Color.parseColor("#2B3038"));


            }
        });

        layout_header_step_secound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                register_step_one.setVisibility(View.GONE);

                register_step_third.setVisibility(View.GONE);

                register_step_two.setVisibility(View.VISIBLE);


                layout_next.setVisibility(View.GONE);

                first_done.setVisibility(View.VISIBLE);

                carHead.setBackgroundColor(Color.parseColor("#4e4f51"));
                personalHead.setBackgroundColor(Color.parseColor("#2B3038"));
                rateHead.setBackgroundColor(Color.parseColor("#2B3038"));

            }
        });

        layout_header_step_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                register_step_one.setVisibility(View.GONE);

                register_step_two.setVisibility(View.GONE);

                register_step_third.setVisibility(View.VISIBLE);

                layout_next.setVisibility(View.VISIBLE);

                second_done.setVisibility(View.VISIBLE);

                first_done.setVisibility(View.VISIBLE);

                rateLinearBottom.setVisibility(View.GONE);

                carHead.setBackgroundColor(Color.parseColor("#2B3038"));
                personalHead.setBackgroundColor(Color.parseColor("#2B3038"));
                rateHead.setBackgroundColor(Color.parseColor("#4e4f51"));

            }
        });

        layout_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUpIntent = new Intent(ActivitySignupMain.this,HomeActivity.class);
                startActivity(signUpIntent);

                third_done.setVisibility(View.VISIBLE);
            }
        });


        expiryDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ActivitySignupMain.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        uploadLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create custom dialog object
                final Dialog dialog = new Dialog(ActivitySignupMain.this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.custom_licence_image_picker);
                // Set dialog title
                dialog.setTitle("Custom Dialog");


                dialog.show();
                upload_profile = (LinearLayout) dialog.findViewById(R.id.upload_liceance);
                upload_img = (ImageView) dialog.findViewById(R.id.licease_pic);

                upload_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySignupMain.this);
                        builder
                                .setMessage(R.string.dialog_select_prompt)
                                .setPositiveButton(R.string.dialog_select_gallery, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startGalleryChooser();
                                    }
                                })
                                .setNegativeButton(R.string.dialog_select_camera, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startCamera();
                                    }
                                });
                        builder.create().show();
                    }
                });

                Button acceptbutton = (Button) dialog.findViewById(R.id.acceptButton);
                // if decline button is clicked, close the custom dialog
                acceptbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upload_success.setVisibility(View.VISIBLE);
                        // Close dialog
                        dialog.dismiss();
                    }
                });

                Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
                // if decline button is clicked, close the custom dialog
                declineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });

            }
        });

        uploadCarpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create custom dialog object
                final Dialog dialog = new Dialog(ActivitySignupMain.this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.custom_licence_image_picker);
                // Set dialog title
                dialog.setTitle("Custom Dialog");


                dialog.show();
                upload_profile = (LinearLayout) dialog.findViewById(R.id.upload_liceance);
                upload_img = (ImageView) dialog.findViewById(R.id.licease_pic);
                txtName = (TextView)dialog.findViewById(R.id.txtName);

                txtName.setText("Upload Car Photo");

                upload_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySignupMain.this);
                        builder
                                .setMessage(R.string.dialog_select_prompt)
                                .setPositiveButton(R.string.dialog_select_gallery, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startGalleryChooser();
                                    }
                                })
                                .setNegativeButton(R.string.dialog_select_camera, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startCamera();
                                    }
                                });
                        builder.create().show();
                    }
                });

                Button acceptbutton = (Button) dialog.findViewById(R.id.acceptButton);
                // if decline button is clicked, close the custom dialog
                acceptbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upload_success2.setVisibility(View.VISIBLE);
                        // Close dialog
                        dialog.dismiss();
                    }
                });

                Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
                // if decline button is clicked, close the custom dialog
                declineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });

            }
        });

        // Country spinner adapter
        ArrayAdapter country_adapter = ArrayAdapter.createFromResource(
                this, R.array.countries, android.R.layout.simple_spinner_item);
        country_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(country_adapter);

        // state spinner adapter
        ArrayAdapter state_adapter = ArrayAdapter.createFromResource(
                this, R.array.state, android.R.layout.simple_spinner_item);
        state_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(state_adapter);

        // city spinner adapter
        ArrayAdapter city_adapter = ArrayAdapter.createFromResource(
                this, R.array.city, android.R.layout.simple_spinner_item);
        city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(city_adapter);

        // car spinner adapter
        ArrayAdapter car_adapter = ArrayAdapter.createFromResource(
                this, R.array.car, android.R.layout.simple_spinner_item);
        car_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        car.setAdapter(car_adapter);

    }

    public void startGalleryChooser() {
        if (PermissionUtils.requestPermission(this, GALLERY_PERMISSIONS_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select a photo"),
                    GALLERY_IMAGE_REQUEST);
        }
    }

    public void startCamera() {
        if (PermissionUtils.requestPermission(
                this,
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            File f = new File(Environment.getExternalStorageDirectory(), "POST_IMAGE.jpg");
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
            imageToUploadUri = Uri.fromFile(f);
            startActivityForResult(cameraIntent, CAMERA_IMAGE_REQUEST);

        }
    }

    public File getCameraFile() {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }


    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, CAMERA_PERMISSIONS_REQUEST, grantResults)) {
                    startCamera();
                }
                break;
            case GALLERY_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, GALLERY_PERMISSIONS_REQUEST, grantResults)) {
                    startGalleryChooser();
                }
                break;
        }
    }

    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(getContentResolver(), uri),
                                1200);

                upload_img.setImageBitmap(bitmap);

            } catch (IOException e) {
                Log.d(TAG, "Image picking failed because " + e.getMessage());
                Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(TAG, "Image picker gave us a null image.");
            Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
    }


    public Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            uploadImage(data.getData());
        } else if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
//            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
            Uri photoUri = imageToUploadUri;
            Log.e("@@", photoUri + "");
            uploadImage(photoUri);
        }
    }


}
