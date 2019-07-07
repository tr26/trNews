package com.example.trnews.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trnews.R;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.imageButton)
    ImageButton mImageButton;
    @BindView(R.id.titleSearchActivity)
    LinearLayout mLinearLayoutFirst;
    @BindView(R.id.textViewTitle)
    TextView mTextViewTitle;
    @BindView(R.id.editTextSearch)
    EditText mEditTextSearch;
    @BindView(R.id.textView4)
    TextView mTextViewDateOne;
    @BindView(R.id.textView3)
    TextView mTextViewDateTwo;
    @BindView(R.id.editText5)
    TextView mEditTextDateOne;
    @BindView(R.id.editText4)
    TextView mEditTextDateTwo;
    @BindView(R.id.checkBox)
    CheckBox mCheckBox1;
    @BindView(R.id.checkBox2)
    CheckBox mCheckBox2;
    @BindView(R.id.checkBox3)
    CheckBox mCheckBox3;
    @BindView(R.id.checkBox4)
    CheckBox mCheckBox4;
    @BindView(R.id.checkBox5)
    CheckBox mCheckBox5;
    @BindView(R.id.checkBox6)
    CheckBox mCheckBox6;
    @BindView(R.id.searchBtn)
    Button mSearchBtn;
    Intent mIntent;
    @BindView(R.id.enableTextView)
    TextView enableTextView;
    @BindView(R.id.switchBtn)
    Switch mSwitchBtn;
    private Context mContext;
    private ArrayList<String> myarray;
    private String tttttt;

    String dateBegin;
    String dateEnd;

    DatePickerDialog mDatePickerDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mContext = this.getBaseContext();
        Intent intent = getIntent();
        Log.d("toto", intent.getStringExtra("title").toString());
        ButterKnife.bind(this);
        mSearchBtn.setEnabled(false);
        mSearchBtn.setBackgroundColor(Color.parseColor("#326F88"));
        this.tttttt = "";

        this.dateBegin = "";
        this.dateEnd = "";

        mEditTextSearch.setText("", TextView.BufferType.EDITABLE);
        mEditTextDateOne.setText("", TextView.BufferType.EDITABLE);
        mEditTextDateTwo.setText("", TextView.BufferType.EDITABLE);


        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        });


        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkTheDifferentFields()){
                        Toast.makeText(view.getContext(), "ttt", Toast.LENGTH_SHORT);
                } else {

                    String text = mEditTextSearch.getText().toString();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    try {
                        Date date1 = sdf.parse(dateBegin);
                        Date date2 = sdf.parse(dateEnd);

                        if (areDateValide(date1, date2)) {

                            Intent intent = new Intent(view.getContext(), ResultActivity.class);
                            intent.putExtra("dateBegin", dateBegin);
                            intent.putExtra("dateEnd", dateEnd);
                            intent.putExtra("categories", tttttt);
                            intent.putExtra("queryWords", text);

                            mContext.startActivity(intent);
                        } else {
                            Toast.makeText(SearchActivity.this, "Les dates que vous venez de rentrer ne sont pas valides", Toast.LENGTH_SHORT);
                        }
                    } catch (ParseException ex) {
                        Log.v("Exception", ex.getLocalizedMessage());
                    }
                }
            }
        });

        mEditTextDateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this, R.style.SwitchCompatTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month +=1;

                        if (month < 10 && day>10){
                            mEditTextDateOne.setText(year + "/" + 0+month + "/" + day);
                        } else if (month >10 && day<10){
                            mEditTextDateOne.setText(year + "/" + month + "/" +0+day);
                        } else if (month<10 && day<10){
                            mEditTextDateOne.setText(year + "/" + 0+month + "/" + 0+day);
                        } else {
                            mEditTextDateOne.setText(year + "/" + month + "/" + day);
                        }
                        dateBegin =  mEditTextDateOne.getText().toString().replace("/", "");
                    }
                }, day, month, year);
                //datePickerDialog.setBac
                datePickerDialog.show();
            }
        });
        mEditTextDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this, R.style.SwitchCompatTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month +=1;

                        if (month < 10 && day>10){
                            mEditTextDateTwo.setText(year + "/" + 0+month + "/" + day);
                        } else if (month >10 && day<10){
                            mEditTextDateTwo.setText(year + "/" + month + "/" +0+day);
                        } else if (month<10 && day<10){
                            mEditTextDateTwo.setText(year + "/" + 0+month + "/" + 0+day);
                        } else {
                            mEditTextDateTwo.setText(year + "/" + month + "/" + day);
                        }
                        dateEnd =  mEditTextDateTwo.getText().toString().replace("/", "");
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });
        adjustView();

    }


    private void adjustView(){
        Intent intent = getIntent();

        intent.getStringExtra("title");
        switch (intent.getStringExtra("title")){
            case ("searchActivity"):
                enableTextView.setVisibility(View.INVISIBLE);
                mSwitchBtn.setVisibility(View.INVISIBLE);
                mSearchBtn.setVisibility(View.VISIBLE);
                mTextViewTitle.setText("Search Articles");
                break;

            case ("notificationsActivity"):
                mSwitchBtn.setVisibility(View.VISIBLE);
                enableTextView.setVisibility(View.VISIBLE);
                mSearchBtn.setVisibility(View.INVISIBLE);
                mTextViewTitle.setText("Notifications");
                break;

                default:
        }
    }

    private void makeVisibleAllComponents(){

    }

    private boolean checkTheDifferentFields(){
        if (mEditTextSearch.getText().length() >0 && myarray.size()>0){
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onCheckboxClicked(View view) {
        //view.onTouchEvent(mEditTextSearch.clic)
        boolean checked = ((CheckBox) view).isChecked();
        if (myarray == null ){
            myarray=new ArrayList<String>();
            //
            // String str="";

        }
        String str="";
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox:
                if (checked){
                    str = "Arts";
                    myarray.add(str);
                }
                else {
                    //str = "1 deselected";
                    myarray.remove("Arts");
                }
                break;
            case R.id.checkBox2:
                if (checked){
                    str = "Business";
                    myarray.add(str);
                }
                else {
                    //str = "2 deselected";
                    myarray.remove("Business");
                }                break;
            case R.id.checkBox3:
                if (checked){
                    str = "Entrepreneures";
                    myarray.add(str);
                }
                else {
                    str = "3 deselected";
                    myarray.remove("Entrepreneures");
                }                break;
            case R.id.checkBox4:
                if (checked){
                    str = "Politics";
                    myarray.add(str);
                }
                else {
                    str = "4 deselected";
                    myarray.remove("Politics");
                }                break;
            case R.id.checkBox5:
                if (checked){
                    str = "Sports";
                    myarray.add(str);
                }
                else {
                    str = "5 deselected";
                    myarray.remove("Sports");
                }                break;
            case R.id.checkBox6:
                if (checked){
                    str = "Travel";
                    myarray.add(str);
                }
                else {
                    str = "6 deselected";
                    myarray.remove("Travel");
                }                break;
        }

        tttttt = TextUtils.join(" ", myarray);

        mEditTextSearch.getText().length();

        if (myarray.size()>0 && mEditTextSearch.getText().length() != 0){
            mSearchBtn.setEnabled(true);
            mSearchBtn.setBackgroundColor(Color.parseColor("#21466C"));
        } else{
            mSearchBtn.setEnabled(false);
            mSearchBtn.setBackgroundColor(Color.parseColor("#326F88"));

        }

        Toast.makeText(getApplicationContext(), tttttt, Toast.LENGTH_SHORT).show();
    }

    private void goToResultActivity(){

        if (!checkTheDifferentFields()){
            Toast.makeText(this, "Sorry but the editText should be enter and at least, one checkbox should have benne choosen", Toast.LENGTH_SHORT);
        } else {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("categories", myarray);
            intent.putExtra("queryWords", "la mere à toto");

            mContext.startActivity(intent);

        }

    }

    private Boolean areDateValide(Date srcDate, Date destDate){

            long startTime = srcDate.getTime();
            long destTime = destDate.getTime();
            long deltaTime = destTime - startTime;
            long oneDayTime = 24 * 60 * 60 * 1000;


            double deltaDay = Math.abs(deltaTime / oneDayTime);
            if (deltaDay > 0) {
                return true;
            } else return false;
    }
}



