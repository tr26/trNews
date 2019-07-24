package com.example.trnews.Activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trnews.Model.ArticlesMostPopular;
import com.example.trnews.Model.ArticlesResultsResearch;
import com.example.trnews.Model.Doc;
import com.example.trnews.Model.MyBroadcastReceiver;
import com.example.trnews.R;
import com.example.trnews.Utils.NYTimesDataService;
import com.example.trnews.Utils.RetrofitInstance;
import com.example.trnews.Views.ResultatsAdapter;

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
import retrofit2.Call;
import retrofit2.Callback;

public class SearchActivity extends AppCompatActivity {

    public static final String API_KEY = "QAevoCdrGFGgi7mRDu8GBOME5RLn3veP";


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

    SharedPreferences sharedPreferences;
    private static final String CATEGORIES_CHECKED = "CATEGORIES_CHECKED";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mContext = this.getBaseContext();
        Intent intent = getIntent();
        ButterKnife.bind(this);
        mSearchBtn.setEnabled(false);
        mSearchBtn.setBackgroundColor(Color.parseColor("#326F88"));
        this.tttttt = "";

        this.dateBegin = "";
        this.dateEnd = "";

        mEditTextSearch.setText("", TextView.BufferType.EDITABLE);
        mEditTextDateOne.setText("", TextView.BufferType.EDITABLE);
        mEditTextDateTwo.setText("", TextView.BufferType.EDITABLE);
        sharedPreferences  = getSharedPreferences(CATEGORIES_CHECKED, 0);
        myarray = new ArrayList<>();



        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        });

        memoriseNotifButton();


        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkTheDifferentFields()){
                        Toast.makeText(view.getContext(), "ttt", Toast.LENGTH_SHORT);
                } else {
                    String text = mEditTextSearch.getText().toString();
                    if (dateBegin != "" || dateEnd != ""){
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
                    } else {
                        Intent intent = new Intent(view.getContext(), ResultActivity.class);
                        intent.putExtra("dateBegin", "");
                        intent.putExtra("dateEnd", "");
                        intent.putExtra("categories", tttttt);
                        intent.putExtra("queryWords", text);

                        mContext.startActivity(intent);
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

        mSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);

                // only if cases are checked
                if (myarray.isEmpty()){
                    mSwitchBtn.setChecked(false);
                    Toast.makeText(getApplicationContext(), "veuillez cocher une case", Toast.LENGTH_SHORT).show();

                }
                else {
                    if (b){
                        //Récuperer les donnés pour requetes
                        //démarrer Notification via AlarmManager

                        //sharedPreferences  = getSharedPreferences(CATEGORIES_CHECKED, 0);
                        sharedPreferences.edit().putString(CATEGORIES_CHECKED, tttttt).apply();

                        mTextViewTitle.setVisibility(View.INVISIBLE);

                    } else {
                        //Supprimer Les notifications
                        //sharedPreferences = getSharedPreferences(CATEGORIES_CHECKED, 0);
                        if (sharedPreferences.contains(CATEGORIES_CHECKED)){
                            sharedPreferences.edit().clear().apply();
                        }
                        mTextViewTitle.setVisibility(View.VISIBLE);
                        cancelAlarm(alarmManager, pendingIntent2);
                    }
                }


                //sharedPreferences.getAll();
            }
        });
        adjustView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        final String[] firstId = new String[1];
        if (mSwitchBtn.isChecked()){
            sharedPreferences.edit().putString(CATEGORIES_CHECKED, tttttt).apply();

            NYTimesDataService nyTimesDataService = RetrofitInstance.getRetrofitInstance().create(NYTimesDataService.class);
            Call<ArticlesResultsResearch> call = nyTimesDataService.getFirstOfTheRequest(tttttt, API_KEY);

            call.enqueue(new Callback<ArticlesResultsResearch>() {
                @Override
                public void onResponse(Call<ArticlesResultsResearch> call, retrofit2.Response<ArticlesResultsResearch> response) {
                    //StringBuilder stringBuilder = new StringBuilder();
                    List<Doc> articlesResearchList;
                    articlesResearchList = response.body().getResponse().getDocs();


                    //mResponseList = new ArrayList<Doc>();
                    firstId[0] = articlesResearchList.get(0).getId();

                    sharedPreferences.edit().putString("firstId", firstId[0]).apply();
                    //NYTimesDataService.getFirstOfTheRequest(tttttt, API_KEY);
                    sharedPreferences.getAll();

                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                    Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);

                    startAlarm(alarmManager, pendingIntent2);
                }

                @Override
                public void onFailure(Call<ArticlesResultsResearch> call, Throwable t) {

                }
            });



        }

    }

    //méomoriser le boutons notif
    private void memoriseNotifButton(){
        if(sharedPreferences.contains(CATEGORIES_CHECKED)){
            mSwitchBtn.setChecked(true);
        } else  mSwitchBtn.setChecked(false);
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
                mEditTextDateTwo.setVisibility(View.INVISIBLE);
                mEditTextDateOne.setVisibility(View.INVISIBLE);
                mTextViewDateOne.setVisibility(View.INVISIBLE);
                mTextViewDateTwo.setVisibility(View.INVISIBLE);
                break;

                default:
        }
    }


    private boolean checkTheDifferentFields(){
        if (mEditTextSearch.getText().length() >0 && myarray.size()>0){
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        if (myarray == null ) { myarray=new ArrayList<String>(); }
        String str="";

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox:
                fillTheArray(myarray, mCheckBox1.getText().toString(), checked);
                break;
            case R.id.checkBox2:
                fillTheArray(myarray, mCheckBox2.getText().toString(), checked);
                break;
            case R.id.checkBox3:
                fillTheArray(myarray, mCheckBox3.getText().toString(), checked);
                break;
            case R.id.checkBox4:
                fillTheArray(myarray, mCheckBox4.getText().toString(), checked);
                break;
            case R.id.checkBox5:
                fillTheArray(myarray, mCheckBox5.getText().toString(), checked);
                break;
            case R.id.checkBox6:
                fillTheArray(myarray, mCheckBox6.getText().toString(), checked);
                break;
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

    public void fillTheArray(List<String> array, String element, Boolean condition){
        if (condition == true){
            array.add(element);
        } else array.remove(element);
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
    private void startAlarm(AlarmManager alarmManager, PendingIntent pendingIntent){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 26);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 90000, pendingIntent);

    }
    private void cancelAlarm(AlarmManager alarmManager, PendingIntent pendingIntent){
        alarmManager.cancel(pendingIntent);
        Toast.makeText(getApplicationContext(), "Alarm Cancelled", Toast.LENGTH_LONG).show();
    }


}



