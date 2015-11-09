package group_14.software_engineering_project_group_14_bles;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/8.
 */
public class FireStationList
{
    ArrayList<FireStation> fireStationList;

    public FireStationList()
    {
        fireStationList = new ArrayList<FireStation>(10);
    }


    public void addFireStation(FireStation fireStation)
    {
        fireStationList.add(fireStation);
    }

    public FireStation getFireStation(int ID)
    {
        return fireStationList.get(ID);
    }

    public int getFireStationNumber()
    {
        return fireStationList.size();
    }

}

class FireStation
{
    private int ID;
    private LatLng latLng;
    private String url;
    private String address;

    public FireStation(int ID,LatLng latLng,String url,String address)
    {
        ID = this.ID;
        latLng = this.latLng;
        url = this.url;
        address = this.address;
    }

}