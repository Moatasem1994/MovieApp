package com.example.eltaysser.mymovies;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    final GetMovies getMovies=new GetMovies(MainActivity.this);
    private RecyclerView recyclerView;

//      AsyncTask asyncTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // recycleView Needed it to Show The Data Received it from server.
          recyclerView=findViewById(R.id.views);
      // implement connect process with internet in AsyncThread .


          SetRecycleView(ConstantValue.url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Get The Menu Layout in the Main Activity.
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return true;
    }

    // What Happen if User Click on any Items from Menu.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort_by_popular:
         SetRecycleView(ConstantValue.urlPopular);
                return true;
             case R.id.sort_vote:
                 SetRecycleView(ConstantValue.urlVote);
           return true;

        default:return super.onOptionsItemSelected(item);
        }
    }

    private void SetRecycleView(String url){
        new AsyncClass(MainActivity.this, new AsyncClass.Get() {
            @Override
            public void setAdapter(final ArrayList<LayoutContent> layoutContents) {
                recyclerView.setAdapter(new com.example.eltaysser.mymovies.AdapterView(layoutContents, MainActivity.this, new com.example.eltaysser.mymovies.AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //your code here , When User Click On Any Items of Views , Go To New Activity Contain The Details.
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    LayoutContent layoutContent=layoutContents.get(position);
                      intent.putExtra("layoutContent",layoutContent);
                        startActivity(intent);
                    }
                }));

                // here Manage How RecycleView Show in Activity.
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                recyclerView.setHasFixedSize(true);

            }

        }).execute(url);
    }
}
