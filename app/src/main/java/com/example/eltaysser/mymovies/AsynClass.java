package com.example.eltaysser.mymovies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;


@SuppressLint("StaticFieldLeak")
class AsyncClass extends AsyncTask<String, Void, Void> {
    // i do prams is String Because i Need For URL Path .
    // i do Progress Void Because When The Task Is Running I don't Need To Send any Value For The Main Thread.
    // i do Result ArrayList<LayoutContent> Because I Need This For Adapter RecycleView.
    // Here We Need For Context And RecycleView
    private final Get get;
    public AsyncClass(Context context,Get get) {
        this.context = context;
        this.get=get;
        //this.recyclerView=recyclerView;
    }

    private  Context context;



    // private final RecyclerView recyclerView;


    @Override
    protected Void doInBackground(String... strings) {
        String url = strings[0]; // that's the url you want visit it
        // inside this class --> GetMovies you make connection with internet
        // and get the ArrayList of type LayoutContent and This Class contain Method GetArray
        //  that's return ArrayList of type LayoutContent , items will show in recycleView
        GetMovies getMovies = new GetMovies(context);
        getMovies.GetArray(url, new GetMovies.VolleyCallBack() {
            @Override
            public void onSuccessResponse(ArrayList<LayoutContent> layoutContents) {
                get.setAdapter(layoutContents);
            }
        });
        return null;
    }



public interface Get{
        void setAdapter(ArrayList<LayoutContent>layoutContents);
}
}
