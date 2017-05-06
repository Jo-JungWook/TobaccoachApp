package com.example.jojungwook.tobaccoachapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jojungwook on 2017. 5. 6..
 */

public class MyDeviceDataBaseOpenHelper extends SQLiteOpenHelper{

    public static final String tableName = "MYSMOKING";


    public MyDeviceDataBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
            수정요망!
            이름은 MYSMOKING이고 아직 기본키와 컬럼들은 정하지 못했다.
         */
        db.execSQL("CREATE TABLE "+ tableName +" ();");
    }

    //DB 업그레이드를 위해서 버전이 변경될때 호출되는 함수.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(SQLiteDatabase db, int todaySmoking, int averageSmoking) {
        //읽고 쓰기가 가능하게 DB열기
        db = getWritableDatabase();
        /*
            수정요망!
            DB에 입력한 값으로 행 추가
            임의로 값을 오늘 흡연량(todaySmoking), 평균 흡연량(averageSmoking)만
            넣어두었다.
         */
        db.execSQL("INSERT INTO "+ tableName + " VALUES(null," + todaySmoking + "," + averageSmoking + ")");
        db.close();
    }

    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM "+ tableName , null);

        while (cursor.moveToNext()) {
            result += "오늘의 흡연량 : "
                    + cursor.getString(0)
                    + " | 평균 흡연량"
                    + cursor.getString(1)
                    + "\n";
        }
        return  result;
    }
}
