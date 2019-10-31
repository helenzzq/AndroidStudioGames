package com.example.myapplication.pushbox;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class PushBoxInitialData {
    public static final int DEFAULT_ROW_NUM = 11;
    public static final int DEFAULT_COLUMN_NUM = 11;
    public static PushBoxLevelInitialData gameLevel = new PushBoxLevelInitialData(12, 12, LEVEL_1);

    //游戏区单元格放了什么
    public static final char NOTHING = ' ';         //empty cell
    public static final char BOX = 'B';             //a box in the cell
    public static final char FLAG = 'F';            //a flag in the cell, destination of the box
    public static final char PRINCE = 'P';              //Prince's location
    public static final char WALL = 'W';             //wall
    public static final char PRINCE_FLAG = 'R';        //a cell with flag and Prince
    public static final char BOX_FLAG = 'X';        //a cell with box and flag

    public static final String [] LEVEL_1 = {
            "            ",
            "            ",
            "  WWWWWWW   ",
            "  W FFB W   ",
            "  W W B W   ",
            "  W W W W   ",
            "  W BMW W   ",
            "  WFB   W   ",
            "  WFWWWWW   ",
            "  WWW       ",
            "            ",
            "            "
    };

    public static final String CONFIG_FILE_NAME = "level_list.txt";
    //    public static final String CONFIG_FILE_NAME = "test_level_list.txt";
    public static void readInitialData(Resources res, String game_map) throws IOException {
        try {
            InputStreamReader inputReader = new InputStreamReader(res.getAssets().open(confgFileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            readConfig(bufReader);
        } catch (IOException e) {
//            Toast.makeText(context, "无法打开配置文件，程序不得不退出。", Toast.LENGTH_LONG).show();
            throw e;
        }
    }

    private static void readConfig(BufferedReader bufReader) throws IOException {
        String line = "";
        while ((line = bufReader.readLine()) != null) {
            line = line.trim();
            if (line.length() < 3) continue;   //该行内容无效
            if (line.charAt(0) == '\\' && line.charAt(1) == '\\') continue;  //注释行
            if (line.charAt(0) == '[' && line.charAt(line.length() - 1) == ']') {
                String label = line.substring(1, line.length() - 1);   //不包括line.length() - 1

                if (Character.isDigit(label.charAt(0))) {       //关卡
                    int level = Integer.parseInt(label);
                    String strRowColumnNum = bufReader.readLine();
                    PushBoxLevelInitialData levelData = readRowColumnNum(strRowColumnNum);
                    for (int r = 0; r < levelData.mRowNum; r++) {
                        levelData.mInitialState[r] = bufReader.readLine();
                        if (levelData.mInitialState[r] == null)
                            throw new IOException("配置文件中，第" + level + "关的行数不足。");
                        if (levelData.mInitialState[r].length() < levelData.mColumnNum)
                            throw new IOException("配置文件中，第" + level + "关第" + r + "行的列数不足。");
                    }
                    GameLevels.add(levelData);
                }
            }
        }
    }

    private static PushBoxLevelInitialData readRowColumnNum(String strRowColumnNum) {
        StringTokenizer stringTokenizer = new StringTokenizer(strRowColumnNum);
        String strRowNum = stringTokenizer.nextToken();   //默认以空格作为分隔符
        String strColumnNum = stringTokenizer.nextToken();
        int rowNum = Integer.parseInt(strRowNum);
        int columnNum = Integer.parseInt(strColumnNum);
        PushBoxLevelInitialData levelData = new PushBoxLevelInitialData(rowNum, columnNum);
        return levelData;
    }
}
