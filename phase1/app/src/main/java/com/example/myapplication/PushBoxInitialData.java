package com.example.myapplication;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class PushBoxInitialData {
    int mRowNum;
    int mColumnNum;

    String [] mInitialState;

    public PushBoxInitialData(int rowNum, int columnNum){
        mRowNum = rowNum;
        mColumnNum = columnNum;
        mInitialState = new String[rowNum];

    }

    public static final int DEFAULT_ROW_NUM = 11;
    public static final int DEFAULT_COLUMN_NUM = 11;
    public static ArrayList<PushBoxLevelInitialData> GameLevels = new ArrayList<>();

    //what we put in the cell

    public static final char NOTHING = ' ';         //nothing in the cell
    public static final char BOX = 'B';             //box in the cell
    public static final char FLAG = 'F';            //flag, the destination of the box, in the cell
    public static final char MAN = 'M';              //the character the moves the box
    public static final char WALL = 'W';             //wall
    public static final char MAN_FLAG = 'R';        //the character and the flag
    public static final char BOX_FLAG = 'X';        //the box and the flag


    public static final String[] level_1 = {
            "  WWWW ",
            "  WF W ",
            "  WB W ",
            "  WM W ",
            "  WWWW ",
            "       ",
            "       "
    };

    public static void addPushGameInitialData(){
        GameLevels.add(new PushBoxLevelInitialData(7, 7, level_1));
    }
    public static void readInitialData(Resources res, String confgFileName) throws IOException {
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
