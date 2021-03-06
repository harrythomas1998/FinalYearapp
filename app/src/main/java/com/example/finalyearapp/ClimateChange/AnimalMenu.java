package com.example.finalyearapp.ClimateChange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalyearapp.R;

import java.util.ArrayList;

public class AnimalMenu extends AppCompatActivity {    private RecyclerView recyclerView;
    private ArrayList<Animal> imageModelArrayList;
    private animaladapter adapter;

    private int[] myImageList = new int[]{R.drawable.penguincycle, R.drawable.pandacycle,R.drawable.sealcycle, R.drawable.tigercycle,R.drawable.polarbearcycle,R.drawable.slothcycle,R.drawable.rhinocycle, R.drawable.elephantcycle, R.drawable.monkeycycle,R.drawable.gturtlecycle, R.drawable.sleopardcycle, R.drawable.turtlecycle};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_menu);




        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        imageModelArrayList = quizzes();
        adapter = new animaladapter(this, imageModelArrayList);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private ArrayList<Animal> quizzes(){

        ArrayList<Animal> list = new ArrayList<>();

        for(int i = 0; i < 12; i++){
            Animal animal = new Animal();
            animal.setImage_drawable(myImageList[i]);
            if(i == 0)
            {
                animal.setName("penguin");
            }
            else if(i == 1)
            {
                animal.setName("panda");
            }
            else if(i == 2)
            {
                animal.setName("seals");
            }
            else if(i == 3)
            {
                animal.setName("tiger");
            }   else if(i == 4)
            {
                animal.setName("polarbear");
            }
            else if(i == 5)
            {
                animal.setName("sloth");
            }
            else if(i == 6)
            {
                animal.setName("indianrhino");
            }
            else if(i == 7)
            {
                animal.setName("asianelephant");
            }
            else if(i == 8)
            {
                animal.setName("spidermonkey");
            }
            else if(i == 9)
            {
                animal.setName("greenturtle");
            }
            else if(i == 10)
            {
                animal.setName("snowleopard");
            }
            else if(i == 11)
            {
                animal.setName("seaturtles");
            }



            list.add(animal);
        }

        return list;
    }






    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }


}