package group_14.software_engineering_project_group_14_bles;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    Button ToMap;
    Button Initialize;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //UserDbHelper userDbHelper;
        //int i =0;
        //userDbHelper = new UserDbHelper(context);
        //userDbHelper.addInformations(userDbHelper, "815 Goyeau St", "42.3118656219999", "-83.0334707361", "Fire Hall #1");
        //userDbHelper.addInformations(userDbHelper,"3121 Milloy St","42.3068574909","-82.9869316877999","Fire Hall #2");
        //userDbHelper.addInformations(userDbHelper,"1905 Cabana Rd W","42.2531537838999","-83.0239833768","Fire Hall #5");
        //userDbHelper.addInformations(userDbHelper,"2750 Ouellette Ave","42.2821369613999","-83.0156976205999","Fire Hall #3");
        //userDbHelper.addInformations(userDbHelper,"2600 College Ave","42.2999231237999","-83.0628378768","Fire Hall #4");
        //userDbHelper.addInformations(userDbHelper,"1380 Matthew Brady Blvd","42.3215477939","-82.9386634266","Fire Hall #7");
        //userDbHelper.addInformations(userDbHelper, "Windsor Airport", "42.2692392288999", "-82.9662277009", "Fire Hall #6");
        //Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_LONG).show();
        //userDbHelper.close();
        /*
        Cursor CR = userDbHelper.getInformations(userDbHelper);
        if(CR.moveToFirst())
        {
            do
            {
                //fsl[i].setAddress(CR.getString(0));
                double coordinate_x = Double.parseDouble(CR.getString(1));
                double coordinate_y = Double.parseDouble(CR.getString(2));
                fireStationArrayList.add(i,new FireStation(i,CR.getString(0),coordinate_x,coordinate_y,CR.getString(3)));

                //fs_coordinates[i] = new LatLng(coordinate_x,coordinate_y);
                //mMap.addMarker(new MarkerOptions().position(new LatLng(coordinate_x,coordinate_y)));
                //fsl[i].setX(coordinate_x);
                //fsl[i].setY(coordinate_y);
                //fsl[i].setOtherinfo(CR.getString(3));
                i++;
            }while(CR.moveToNext());
        }
        Toast.makeText(getBaseContext(),"i = " + i ,Toast.LENGTH_LONG).show();
        */

        ToMap = (Button)findViewById(R.id.ToMap);
        ToMap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MapsActivity.fireStationLocationList.clear();
                /*
                UserDbHelper userDbHelper;
                userDbHelper = new UserDbHelper(context);
                int i =0;

                Cursor CR = userDbHelper.getInformations(userDbHelper);
                if(CR.moveToFirst())
                {
                    do
                    {
                        //fsl[i].setAddress(CR.getString(0));
                        double coordinate_x = Double.parseDouble(CR.getString(1));
                        double coordinate_y = Double.parseDouble(CR.getString(2));


                        test.add(coordinate_x);
                        Toast.makeText(getBaseContext(),"Test X1 = " + test.get(i), Toast.LENGTH_LONG).show();
                        //fs_coordinates[i] = new LatLng(coordinate_x,coordinate_y);
                        //mMap.addMarker(new MarkerOptions().position(new LatLng(coordinate_x,coordinate_y)));
                        //fsl[i].setX(coordinate_x);
                        //fsl[i].setY(coordinate_y);
                        //fsl[i].setOtherinfo(CR.getString(3));
                        i++;
                    }while(CR.moveToNext());
                }

                Toast.makeText(getBaseContext(),"i = " + i ,Toast.LENGTH_LONG).show();
                */

                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, MapsActivity.class);
                /*
                for(int j =0; j<fireStationArrayList.size();j++)
                {
                    //Toast.makeText(getBaseContext(),"X = " + fireStationArrayList.get(j).getX() ,Toast.LENGTH_LONG).show();
                    MapsActivity.fireStationLocationList.add(j,new LatLng(fireStationArrayList.get(j).getX(),fireStationArrayList.get(j).getY()));
                }*/
                //Toast.makeText(getBaseContext(),"fireStationArrayList size = " + fireStationArrayList.size() ,Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

        Initialize = (Button)findViewById(R.id.Initialize);
        Initialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserDbHelper userDbHelper;
                userDbHelper = new UserDbHelper(context);
                userDbHelper.deleteInformation(userDbHelper,Location_ini.NewCoordinateInfo.TABLE_NAME);
                userDbHelper.addFacilityInformations(userDbHelper,"1", "815 Goyeau St", "42.3118656219999", "-83.0334707361", "Fire Hall #1","FireStation");
                userDbHelper.addFacilityInformations(userDbHelper,"2", "3121 Milloy St", "42.3068574909", "-82.9869316877999", "Fire Hall #2","FireStation");
                userDbHelper.addFacilityInformations(userDbHelper,"3", "1905 Cabana Rd W", "42.2531537838999", "-83.0239833768", "Fire Hall #5","FireStation");
                userDbHelper.addFacilityInformations(userDbHelper,"4", "2750 Ouellette Ave", "42.2821369613999", "-83.0156976205999", "Fire Hall #3","FireStation");
                userDbHelper.addFacilityInformations(userDbHelper,"5", "2600 College Ave", "42.2999231237999", "-83.0628378768", "Fire Hall #4","FireStation");
                userDbHelper.addFacilityInformations(userDbHelper,"6", "1380 Matthew Brady Blvd", "42.3215477939", "-82.9386634266", "Fire Hall #7","FireStation");
                userDbHelper.addFacilityInformations(userDbHelper,"7", "Windsor Airport", "42.2692392288999", "-82.9662277009", "Fire Hall #6","FireStation");
                Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_LONG).show();
                userDbHelper.close();

            }
        });
    }






}
