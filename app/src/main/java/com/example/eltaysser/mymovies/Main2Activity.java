package com.example.eltaysser.mymovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView imageView = findViewById(R.id.imageView);
        TextView tName = findViewById(R.id.tName);
        TextView tOverView = findViewById(R.id.tDesc);
        TextView tVote = findViewById(R.id.vote);
        // here use getParcelableExtra instead of using getBundle
        Intent intent=getIntent();
            LayoutContent layoutContent = intent.getParcelableExtra("layoutContent");
            if (layoutContent!=null) {
        String name=layoutContent.getMovieName();
        String postImage=layoutContent.getImageUrlForPicasso();
        String desc=layoutContent.getDescription();
        String vote=layoutContent.getVoteCount();

        String path = ConstantValue.pathForImage;
        Picasso.get().load(path +postImage).into(imageView);
        tName.setText(name);
        tOverView.setText(desc);
        tVote.setText("Number Of Vote: "+vote);
    }}
}
