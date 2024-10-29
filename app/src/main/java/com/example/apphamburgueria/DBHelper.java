package com.example.apphamburgueria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "meuBanco.db";
    private static final int DATABASE_VERSION = 1;

    // Tabela de usuários
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_SENHA = "senha";
    private static final String COLUMN_TELEFONE = "telefone";

    // Tabela de produtos
    private static final String TABLE_PRODUTOS = "produtos";
    private static final String COLUMN_PRODUTO_ID = "id";
    private static final String COLUMN_PRODUTO_NOME = "nome";
    private static final String COLUMN_PRODUTO_PRECO = "preco";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela de usuários
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_SENHA + " TEXT,"
                + COLUMN_TELEFONE + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        // Criação da tabela de produtos
        String CREATE_PRODUTOS_TABLE = "CREATE TABLE " + TABLE_PRODUTOS + "("
                + COLUMN_PRODUTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRODUTO_NOME + " TEXT,"
                + COLUMN_PRODUTO_PRECO + " REAL" + ")";
        db.execSQL(CREATE_PRODUTOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTOS);
        onCreate(db);
    }

    // Método para verificar se o e-mail e senha do usuário existem
    public boolean checkUser(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_SENHA + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, senha});

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }

    public boolean insertUser(String nome, String email, String senha, String telefone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_SENHA, senha);
        values.put(COLUMN_TELEFONE, telefone);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        return result != -1;

    }
}
