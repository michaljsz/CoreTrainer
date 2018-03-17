package com.michalj.backtrainer;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

@Database(entities = {Set.class}, version = 1)
public abstract class ExerciesDatabase extends RoomDatabase{
    private static final String DB_NAME = "exercisedatabase.db";
    private static volatile ExerciesDatabase instance;
    public abstract SetDao firstSetDao();

    public static ExerciesDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static ExerciesDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                ExerciesDatabase.class,
                DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).firstSetDao().insertWorkout(Set.populateData());
                            }
                        });
                    }
                })
                .allowMainThreadQueries() // to be removed in next update
                .build();
        }



}
