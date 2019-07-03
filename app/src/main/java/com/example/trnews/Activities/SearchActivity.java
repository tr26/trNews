package com.example.trnews.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.trnews.R;

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
    EditText mEditTextDateOne;
    @BindView(R.id.editText4)
    EditText mEditTextDateTwo;
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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mContext = this.getBaseContext();
        Intent intent = getIntent();
        Log.d("toto", intent.getStringExtra("title").toString());
        ButterKnife.bind(this);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        });

        adjustView();

    }

    private void adjustView(){

        //makeVisibleAllComponents();

        Intent intent = getIntent();

        //String searchActivity = "searchActivity";
        //tring notificationsActivity = "notificationActivity";
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
}
