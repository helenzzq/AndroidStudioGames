package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.io.IOException;

public class PushBoxChooseLevelActivity extends Activity {

    public static final String LEVEL = "level";
    private Boolean mGameLevels_PassedOrNot[];

    private PushBoxChooseLevelAdapter chooseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_box_choose_level);
        if (PushBoxInitialData.GameLevels.size() == 0)
            //PushBoxInitialData.addInitGameData();
            try {
                PushBoxInitialData.readInitialData(getResources(), PushBoxInitialData.CONFIG_FILE_NAME);
            } catch (IOException e) {
                Toast.makeText(this, "无法读取配置文件。无法获取关卡数据。退出。", Toast.LENGTH_LONG).show();
                System.exit(-1);
            }
        mGameLevels_PassedOrNot = new Boolean[PushBoxInitialData.GameLevels.size()];
        for (int lvl = 1; lvl <= PushBoxInitialData.GameLevels.size(); lvl++)
            mGameLevels_PassedOrNot[lvl - 1] = PushManager.getPassedLevel(this, lvl);

        chooseAdapter = new PushBoxChooseLevelAdapter(this, R.layout.item_guan_qia_gridview, mGameLevels_PassedOrNot);
        GridView gv_GuanQia = (GridView) findViewById(R.id.gv_xuan_guan_qia);
        gv_GuanQia.setAdapter(chooseAdapter);
//        gv_GuanQia.setOnItemSelectedListener(new GuanQia_ItemSelectedListener());
        gv_GuanQia.setOnItemClickListener(new GV_ItemClickListener());
    }
    @Override
    protected void onResume() {
        super.onResume();
        chooseAdapter.notifyDataSetChanged();  //Possibly, pass one level.
    }

    private class GuanQia_ItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int itemIndex, long l) {
            Toast.makeText(PushBoxChooseLevelActivity.this, "选中了第" + itemIndex + "关", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class GV_ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
            int level = itemIndex + 1;

            Intent intent = new Intent(PushBoxChooseLevelActivity.this, PushBoxGameActivity.class);
            intent.putExtra(LEVEL, level);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xuan_guanqia, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itm_xgq_back:
                finish();
                break;
            case R.id.itm_xgq_exit:
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


