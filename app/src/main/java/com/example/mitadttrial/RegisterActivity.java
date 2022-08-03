package com.example.mitadttrial;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mitadttrial.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity
{
    private EditText editTextRegisterFullName, editTextRegisterEmail, editTextRegisterDoB, editTextRegisterMobile, editTextRegisterPwd, editTextRegisterConfirmPwd;
    private ProgressBar progressBar;
    private RadioGroup radioGroupRegisterGender;
    private RadioButton radioButtonGenderSelected;
    private DatePickerDialog picker;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Register");

        Toast.makeText(RegisterActivity.this, "Your can Register now", Toast.LENGTH_SHORT).show();

        editTextRegisterFullName = findViewById(R.id.editText_register_full_name);
        editTextRegisterEmail = findViewById(R.id.editText_register_email);
        editTextRegisterDoB =  findViewById(R.id.editText_register_dob);
        editTextRegisterMobile = findViewById(R.id.editText_register_mobile);
        editTextRegisterPwd = findViewById(R.id.editText_register_password);
        editTextRegisterConfirmPwd = findViewById(R.id.editText_register_confirm_password);

        //Radio Button for Gender
        radioGroupRegisterGender = findViewById(R.id.radio_group_register_gender);
        radioGroupRegisterGender.clearCheck();

        //Setting up date Picker for edittext
        editTextRegisterDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //Date Picker Dialog
                picker = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth)
                    {
                        editTextRegisterDoB.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year, month, day);
                picker.show();
            }
        });

        progressBar = findViewById(R.id.progressBar);
        Button buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int selectedGenderId = radioGroupRegisterGender.getCheckedRadioButtonId();
                radioButtonGenderSelected = findViewById(selectedGenderId);


                //Obtain the Entered Data
                String textFullName = editTextRegisterFullName.getText().toString();
                String textEmail = editTextRegisterEmail.getText().toString();
                String textDob = editTextRegisterDoB.getText().toString();
                String textMobile = editTextRegisterMobile.getText().toString();
                String textPwd = editTextRegisterPwd.getText().toString();
                String textConfirmPwd = editTextRegisterConfirmPwd.getText().toString();
                String textGender;


                //Validate Mobile Number Using Matcher and Pattern(Regular Expression)
                String mobileRegex = "[6-9][0-9]{9}";               //First Number can be {6,7,8,9} and rest 9 nos. can be any digit
                Matcher mobileMatcher;
                Pattern mobilePattern = Pattern.compile(mobileRegex);
                mobileMatcher = mobilePattern.matcher(textMobile);



                if(TextUtils.isEmpty(textFullName))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter Your Full Name", Toast.LENGTH_SHORT).show();
                    editTextRegisterFullName.setError("Full Name is required");
                    editTextRegisterFullName.requestFocus();
                }
                else if(TextUtils.isEmpty(textEmail))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                    editTextRegisterEmail.setError("Email is required");
                    editTextRegisterEmail.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches())
                {
                    Toast.makeText(RegisterActivity.this, "Please Re-Enter Your Email", Toast.LENGTH_SHORT).show();
                    editTextRegisterEmail.setError("Valid Email is required");
                    editTextRegisterEmail.requestFocus();
                }
                else if(TextUtils.isEmpty(textDob))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter Your Date Of Birth", Toast.LENGTH_SHORT).show();
                    editTextRegisterDoB.setError("Date Of Birth is required");
                    editTextRegisterDoB.requestFocus();
                }
                else if(radioGroupRegisterGender.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter Your Gender", Toast.LENGTH_SHORT).show();
                    radioButtonGenderSelected.setError("Date Of Birth is required");
                    radioButtonGenderSelected.requestFocus();
                }
                else if(TextUtils.isEmpty(textMobile))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter Your Mobile Number", Toast.LENGTH_SHORT).show();
                    editTextRegisterMobile.setError("Mobile Number is required");
                    editTextRegisterMobile.requestFocus();
                }
                else if(!mobileMatcher.find())
                {
                    Toast.makeText(RegisterActivity.this, "Please Re-Enter Your Mobile Number", Toast.LENGTH_SHORT).show();
                    editTextRegisterMobile.setError("Mobile Number is not valid");
                    editTextRegisterMobile.requestFocus();

                } else if(textMobile.length() != 10)
                {
                    Toast.makeText(RegisterActivity.this, "Please Re-Enter Your Mobile Number", Toast.LENGTH_SHORT).show();
                    editTextRegisterMobile.setError("Valid Mobile Number is required");
                    editTextRegisterMobile.requestFocus();
                }
                else if(TextUtils.isEmpty(textPwd))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    editTextRegisterPwd.setError("Password is required");
                    editTextRegisterPwd.requestFocus();
                }
                else if(textPwd.length() < 6)
                {
                    Toast.makeText(RegisterActivity.this, "Password Should be atleast 6 digits", Toast.LENGTH_SHORT).show();
                    editTextRegisterPwd.setError("Password too weak");
                    editTextRegisterPwd.requestFocus();
                }
                else if(TextUtils.isEmpty(textConfirmPwd))
                {
                    Toast.makeText(RegisterActivity.this, "Please Confirm Your Password", Toast.LENGTH_SHORT).show();
                    editTextRegisterConfirmPwd.setError("Password Confirmation Required");
                    editTextRegisterConfirmPwd.requestFocus();
                }
                else if(!textPwd.equals(textConfirmPwd))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter same password", Toast.LENGTH_SHORT).show();
                    editTextRegisterConfirmPwd.setError("Password Confirmation Required");
                    editTextRegisterConfirmPwd.requestFocus();
                    // CLear the Entered Password
                    editTextRegisterPwd.clearComposingText();
                    editTextRegisterConfirmPwd.clearComposingText();
                }
                else
                {
                    textGender = radioButtonGenderSelected.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(textFullName, textEmail, textDob, textGender, textMobile, textPwd, textConfirmPwd);
                }

            }
        });


    }

    private void registerUser(String textFullName, String textEmail, String textDob, String textGender, String textMobile, String textPwd, String textConfirmPwd)
    {
        FirebaseAuth auth= FirebaseAuth.getInstance();

        //Create User Profile
        auth.createUserWithEmailAndPassword(textEmail,textPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    //Update Display Name of User
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(textFullName).build();
                    firebaseUser.updateProfile(profileChangeRequest);


                    //Enter Data into the Firebase Realtime Database
                    ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(textFullName, textDob, textGender, textMobile);
                    Users user = new Users(textFullName, textEmail, textPwd);
                    //Extracting User ReferencesFrom Database for registered Users
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference referenceProfile = database.getReference("Registered Users");



                    referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {

                            if(task.isSuccessful())
                            {
                                //Send Verification Email
                                firebaseUser.sendEmailVerification();
                                Toast.makeText(RegisterActivity.this, "User Successfully Registered. Please Verify Your Email.", Toast.LENGTH_SHORT).show();


                                //Open User Profile after Successful Registration
                                Intent intent = new Intent(RegisterActivity.this, UserProfileActivity.class);
                                //To prevent user from returning back to register activity on pressing back button after registration
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                //To close register activity
                                finish();

                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this, "User Registration Failed. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                            //Hide Progress Bar
                            progressBar.setVisibility(View.GONE);

                        }
                    });
                }
                else
                {
                    try
                    {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e)
                    {
                        editTextRegisterPwd.setError("Your Password is weak. Kindly use a mix of alphabets, numbers and special characters. ");
                        editTextRegisterPwd.requestFocus();
                        progressBar.setVisibility(View.GONE);
                    } catch (FirebaseAuthInvalidCredentialsException e)
                    {
                        editTextRegisterEmail.setError("Your Email is invalid or already in Use. Kindly re-enter your email id.");
                        editTextRegisterEmail.requestFocus();
                        progressBar.setVisibility(View.GONE);
                    } catch (FirebaseAuthUserCollisionException e)
                    {
                        editTextRegisterEmail.setError("User already registered with this email id. Kindly use another email id");
                        editTextRegisterEmail.requestFocus();
                        progressBar.setVisibility(View.GONE);
                    } catch (Exception e)
                    {
                        Log.e(TAG, e.getMessage()) ;
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);

                }
            }
        });

    }

}