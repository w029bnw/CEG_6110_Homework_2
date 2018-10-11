package com.example.brittanywoods.homework2;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Stack;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout parentLinearLayout;
    public Clock clock;
    public AnalogView[] analogarr = new AnalogView[50];
    public DigitalView[] digitalarr = new DigitalView[50];
    public ClockController[] analogcarr = new ClockController[50];
    public ClockController[] digitalcarr = new ClockController[50];
    private int s,h,m,da,mn,y,ai = 0,di = 0;
    public boolean a = false, a1 = true;
    public boolean d = false, d1 = true;
    public Stack<Clock> undoStack = new Stack<Clock>();
    public Stack<Clock> redoStack = new Stack<Clock>();
    public Execute execute = new Execute(undoStack, redoStack);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create calendar instance to get current time and date, then manually set time variables
        // to update model and views
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("EST"));
        s = calendar.get(Calendar.SECOND);
        h = calendar.get(Calendar.HOUR) + 1; //Account for daylight saving time
        m = calendar.get(Calendar.MINUTE);
        mn = calendar.get(Calendar.MONTH) + 1; //The month is always off by one for some reason

        // Create clock model object and pass it relative values, create controllers for analog
        // and digital views
        clock = new Clock(calendar);
        clock.setMonth(mn);

        for(int i = 0; i < 10; i++){
            analogcarr[i] = new ClockController(clock, analogarr[i]);
        }

        for(int i = 0; i < 10; i++){
            digitalcarr[i] = new ClockController(clock, digitalarr[i]);
        }

        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add floating action button to deploy dialog that allows user to change date and time
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CustomDialog dialog = new CustomDialog(MainActivity.this);
                dialog.show();

                final EditText date = (EditText)dialog.findViewById(R.id.date);
                final EditText time = (EditText)dialog.findViewById(R.id.time);
                Button button = (Button) dialog.findViewById(R.id.change);

                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        if(!date.getText().toString().equalsIgnoreCase("MM/DD/YYYY")){
                            Clock temp = new Clock(clock);
                            undoStack.push(temp);
                            clock.setMonth(Integer.parseInt(date.getText().toString().substring(0,2)));
                            clock.setDay(Integer.parseInt(date.getText().toString().substring(3,5)));
                            clock.setYear(Integer.parseInt(date.getText().toString().substring(6,10)));
                            updateClock();
                        }

                        if(!time.getText().toString().equalsIgnoreCase("HH:MM:SS")){
                            h = (Integer.parseInt(time.getText().toString().substring(0,2)));
                            m = (Integer.parseInt(time.getText().toString().substring(3,5)));
                            s = (Integer.parseInt(time.getText().toString().substring(6,8)));
                            Clock temp = new Clock(clock);
                            undoStack.push(temp);
                            updateClock();
                        }

                        dialog.dismiss();
                    }
                });
            }
        });

        // Add drawer that holds options for views and undo/redo
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Create thread to update clocks with. Calendar instance isn't updating time for some reason
        // so logic was added to update the clock model object with and subsequently the views
        final Thread thread = new Thread(){

            @Override
            public void run(){
                try{
                    while(!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(s < 60){
                                    s = s + 1;
                                } else if(s == 60){
                                    s = 1;
                                    if(m < 60){
                                        m = m + 1;
                                    } else if(m == 60){
                                        m = 1;
                                        if(h < 12){
                                            h = h + 1;
                                        } else if(h == 12){
                                            h = 1;
                                        }
                                    }
                                }

                                // Updates clock object
                                updateClock();

                                // Updates views if they exist within the layout
                                if(a) {

                                    for(int i = 0; i < ai; i++){
                                        analogcarr[i].updateView(clock, analogarr[i]);
                                    }
                                }

                                if(d){
                                    for(int i = 0; i < di; i++){
                                        digitalcarr[i].updateView(clock,digitalarr[i]);
                                    }
                                }
                            }
                        });
                    }
                } catch(InterruptedException e){

                }
            }
        };

        thread.start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        // Adds analog view to layout if clicked
        if (id == R.id.add_analog) {
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    addAnalogView();

                    if(a) {
                        if(a1){
                            analogarr[ai] = findViewById(R.id.analogView2);
                            a1 = false;
                        } else{
                            analogarr[ai] = findViewById(R.id.analogView3);
                        }
                    } else{
                        analogarr[0] = findViewById(R.id.analogView);
                    }

                    ai++;
                    a = true;
                    return true;
                }
            });

        // Adds digital view to layout if clicked
        } else if (id == R.id.add_digital) {
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    addDigitalView();

                    if(d) {
                        if(d1){
                            digitalarr[di] = findViewById(R.id.digitalView2);
                            d1 = false;
                        } else{
                            digitalarr[di] = findViewById(R.id.digitalView3);
                        }

                    } else{
                        digitalarr[0] = findViewById(R.id.digitalView);
                    }

                    di++;
                    d = true;
                    return true;
                }
            });

        // Undoes action
        } else if (id == R.id.undo) {
            execute.undo(clock);
            s = clock.getSecond();
            m = clock.getMinute();
            h = clock.getHour();

        // Redoes action
        } else if (id == R.id.redo) {
            execute.redo(clock);
            s = clock.getSecond();
            m = clock.getMinute();
            h = clock.getHour();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Function to add digital clock to layout
    public void addDigitalView(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if(d){
            if(d1){
                view = inflater.inflate(R.layout.digitalclock2, null);
            } else{
                view = inflater.inflate(R.layout.digitalclock3, null);
            }

        } else {
            view = inflater.inflate(R.layout.digitalclock, null);
        }
        parentLinearLayout.addView(view,parentLinearLayout.getChildCount()-1);
    }

    // Function to add analog clock to layout
    public void addAnalogView(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if(a){
            if(a1){
                view = inflater.inflate(R.layout.analogclock2, null);
            } else{
                view = inflater.inflate(R.layout.analogclock3, null);
            }

        } else {
            view = inflater.inflate(R.layout.analogclock, null);
        }
        parentLinearLayout.addView(view,parentLinearLayout.getChildCount()-1);
    }

    // Function to update clock model object's time variables
    public void updateClock(){
        clock.setSecond(s);
        clock.setMinute(m);
        clock.setHour(h);
    }
}
