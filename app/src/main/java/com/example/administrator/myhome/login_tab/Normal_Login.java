package com.example.administrator.myhome.login_tab;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.administrator.myhome.R;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
public class Normal_Login extends AppCompatActivity implements View.OnClickListener,View.OnFocusChangeListener{
    private EditText phone_number,login_pass;
    private ImageView clear_edit_01,clear_edit_02,see_edit_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal__login);
        initView();
    }

    private void initView() {
        phone_number=(EditText)findViewById(R.id.phone_number);
        login_pass=(EditText)findViewById(R.id.login_pass);
        clear_edit_01=(ImageView)findViewById(R.id.clear_edit_01);
        clear_edit_02=(ImageView)findViewById(R.id.clear_edit_02);
        see_edit_pass=(ImageView)findViewById(R.id.see_edit_pass);
        see_edit_pass.setOnClickListener(this);
        clear_edit_01.setOnClickListener(this);
        clear_edit_02.setOnClickListener(this);
        phone_number.addTextChangedListener(new numberChangeListener());
        phone_number.setOnFocusChangeListener(this);
        login_pass.addTextChangedListener(new passwordChangeListener());
        login_pass.setOnFocusChangeListener(this);

            }













    @Override
    public void onClick(View view) {
        TransformationMethod type= login_pass.getTransformationMethod();
        switch (view.getId()){
            case R.id.see_edit_pass:
                if (PasswordTransformationMethod.getInstance().equals(type)){
                    login_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    see_edit_pass.setImageResource(R.drawable.see_pass_no);
                }else {
                    login_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //                   edPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    see_edit_pass.setImageResource(R.drawable.see_pass);
                }
                break;
            case R.id.clear_edit_01:
                phone_number.setText("");
                break;
            case R.id.clear_edit_02:
                login_pass.setText("");
                break;

        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.phone_number:
                if (!b){
                    clear_edit_01.setVisibility(GONE);
                }else {
                    if (phone_number.getText().length()>0){
                        clear_edit_01.setVisibility(VISIBLE);
                    }
                }break;
            case R.id.login_pass:
                if (!b){
                    clear_edit_02.setVisibility(GONE);
                }else {
                    if (login_pass.getText().length()>0){
                        clear_edit_02.setVisibility(VISIBLE);
                    }
                }break;
        }
    }

    /**
 * 监听login_pass内部变化*/

    class passwordChangeListener implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (login_pass.hasFocus()) {
                // 当 输入 字符 长度 不为0 时 显示 删除 按钮
                if (charSequence.length() > 0) {
                    clear_edit_02.setVisibility(VISIBLE);
                } else {
                    clear_edit_02.setVisibility(GONE);
                }
            }else {
                clear_edit_02.setVisibility(GONE);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
    /**
     * 监听phone_number内部变化*/
    class numberChangeListener implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0) {
                // 当 输入 字符 长度 不为0 时 显示 删除 按钮

                    clear_edit_01.setVisibility(VISIBLE);
                } else {
                    clear_edit_01.setVisibility(GONE);
                }

        }

        @Override
        public void afterTextChanged(Editable editable) {

            }
        }
    }

