package com.example.reciclagem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "reciclagem.db";
    private static final int DATABASE_VERSION = 1;

    // Tabela de Pontos de Coleta
    public static final String TABLE_PONTOS_COLETA = "pontos_coleta";
    public static final String COLUMN_ID_PONTO = "id";
    public static final String COLUMN_NOME_PONTO = "nome";
    public static final String COLUMN_ENDERECO = "endereco";
    public static final String COLUMN_MATERIAIS = "materiais";

    // Tabela de Materiais
    public static final String TABLE_MATERIAIS = "materiais";
    public static final String COLUMN_ID_MATERIAL = "id";
    public static final String COLUMN_NOME_MATERIAL = "nome";
    public static final String COLUMN_DESCRICAO = "descricao";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PONTOS_TABLE = "CREATE TABLE " + TABLE_PONTOS_COLETA + "("
                + COLUMN_ID_PONTO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOME_PONTO + " TEXT,"
                + COLUMN_ENDERECO + " TEXT,"
                + COLUMN_MATERIAIS + " TEXT"
                + ")";
        db.execSQL(CREATE_PONTOS_TABLE);

        String CREATE_MATERIAIS_TABLE = "CREATE TABLE " + TABLE_MATERIAIS + "("
                + COLUMN_ID_MATERIAL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOME_MATERIAL + " TEXT,"
                + COLUMN_DESCRICAO + " TEXT"
                + ")";
        db.execSQL(CREATE_MATERIAIS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PONTOS_COLETA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIAIS);
        onCreate(db);
    }

    public void addPontoColeta(String nome, String endereco, String materiais) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME_PONTO, nome);
        values.put(COLUMN_ENDERECO, endereco);
        values.put(COLUMN_MATERIAIS, materiais);
        db.insert(TABLE_PONTOS_COLETA, null, values);
        db.close();
    }

    public List<PontoColeta> getAllPontosColeta() {
        List<PontoColeta> pontosColeta = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PONTOS_COLETA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PONTO));
                String nome = cursor.getString(cursor.getColumnIndex(COLUMN_NOME_PONTO));
                String endereco = cursor.getString(cursor.getColumnIndex(COLUMN_ENDERECO));
                String materiais = cursor.getString(cursor.getColumnIndex(COLUMN_MATERIAIS));
                pontosColeta.add(new PontoColeta(id, nome, endereco, materiais));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return pontosColeta;
    }

    public void addMaterial(String nome, String descricao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME_MATERIAL, nome);
        values.put(COLUMN_DESCRICAO, descricao);
        db.insert(TABLE_MATERIAIS, null, values);
        db.close();
    }

    public List<Material> getAllMateriais() {
        List<Material> materiais = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MATERIAIS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_MATERIAL));
                String nome = cursor.getString(cursor.getColumnIndex(COLUMN_NOME_MATERIAL));
                String descricao = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO));
                materiais.add(new Material(id, nome, descricao));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return materiais;
    }

    public void atualizarPontoColeta(int id, String novoNome, String novoEndereco, String novosMateriais) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME_PONTO, novoNome);
        values.put(COLUMN_ENDERECO, novoEndereco);
        values.put(COLUMN_MATERIAIS, novosMateriais);
        db.update(TABLE_PONTOS_COLETA, values, COLUMN_ID_PONTO + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void atualizarMaterial(int id, String novoNome, String novaDescricao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME_MATERIAL, novoNome);
        values.put(COLUMN_DESCRICAO, novaDescricao);
        db.update(TABLE_MATERIAIS, values, COLUMN_ID_MATERIAL + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void deletarPontoColeta(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PONTOS_COLETA, COLUMN_ID_PONTO + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void deletarMaterial(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MATERIAIS, COLUMN_ID_MATERIAL + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
} 