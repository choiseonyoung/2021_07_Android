package com.callor.cacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.callor.cacao.adapter.ChattAdapter;
import com.callor.cacao.model.Chatt;
import com.callor.cacao.service.FirebaseServiceImplV1;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txt_msg;
    private AppCompatButton btn_send;

    private RecyclerView chatt_list_view;
    private ChattAdapter chattAdapter;
    private List<Chatt> chattList;

    private DatabaseReference dbRef;

    private String nickname = "익명";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        nickname = preferences.getString("nick_name", "익명");
        String alarm = preferences.getString("alarm", "ON");

        Log.d("닉네임", nickname);
        Log.d("알람", alarm);

       /**
         * custom 된 toolbar를 ActionBar로 설정하기 위한 코드
         */
        Toolbar main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);

        /**
         * 새로운 Activity가 열렸을 때
         * 이전 Activity(page)로 돌아가기 아이콘 표시하기
         * MainActivity에서는 의미가 없기 때문에 사용하지 않는다
         */
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);

        chatt_list_view = findViewById(R.id.chatt_list_view);

        chattList = new ArrayList<Chatt>();

//        chattAdapter = new ChattAdapter(chattList);
        // 1-1 App에 등록된 nickname을 Adapter에 데이터와 함께 전달하기
        chattAdapter = new ChattAdapter(chattList, nickname);

        chatt_list_view.setAdapter(chattAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        chatt_list_view.setLayoutManager(layoutManager);

        FirebaseDatabase dbConn = FirebaseDatabase.getInstance();

        dbRef = dbConn.getReference("chatting");

        ChildEventListener childEventListener = new FirebaseServiceImplV1(chattAdapter);

        dbRef.addChildEventListener(childEventListener);

        btn_send.setOnClickListener(view->{
            String msg = txt_msg.getText().toString();
            if(msg != null) {
                String toastMsg = String.format("메시지 : %s", msg);
                Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();

                Chatt chattVO = new Chatt();
                chattVO.setMsg(msg);
                chattVO.setName(nickname);

                Log.d("클릭", chattVO.toString());

                dbRef.push().setValue(chattVO);
                txt_msg.setText("");
            }
        });
    }

    /**
     * Custom한 Toolbar가 (Main)Activity에 적용될 때
     * setSupportActionBar() method가 실행될 때
     * event가 발생하고 자동으로 호출되는 method
     *
     * Toolbar를 사용하여 ActionBar를 Custom하는 이유 중에 하나가
     * onCreateOptionsMenu() method를 Override하여
     * 더욱 세밀한 Customizing을 하기 위해서이다
     *
     * Toolbar에 사용자 정의형 menu를 설정하여
     * 다른 기능을 수행하도록 하는 UI를 구현할 수 있다다
    *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_tool_menu, menu);
        // 이걸 inflate해서 대신 쓰겠다
        return true;
    }

    /**
     * ActionBar에 설정된 Option Menu에 특정한 항목(item)을 클릭하면 호출되는 method
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        int menu_item = item.getItemId();
        // 옵션 클릭하면 클릭한 아이템 값이 여기로 넘어올 것

        if(menu_item == R.id.app_bar_settings) {
            Toast.makeText(this, "설정메뉴 클릭됨", Toast.LENGTH_SHORT).show();

            Intent setting_intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(setting_intent);

            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
}