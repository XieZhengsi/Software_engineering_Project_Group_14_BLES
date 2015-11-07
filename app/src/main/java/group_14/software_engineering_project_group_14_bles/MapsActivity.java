package group_14.software_engineering_project_group_14_bles;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//Import for drawing circle on map
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMarkerDragListener, OnMapLongClickListener,OnMapReadyCallback {

    private GoogleMap mMap;

    private static final LatLng Windsor = new LatLng(42.289810, -82.999313);

    private static final double DEFAULT_RADIUS = 10000;

    public static final double RADIUS_OF_EARTH_METERS = 6371009;

//    private static final int WIDTH_MAX = 50;
//
//    private static final int HUE_MAX = 360;
//
//    private static final int ALPHA_MAX = 255;

    private List<DraggableCircle> mCircles = new ArrayList<DraggableCircle>(1);

//    private SeekBar mColorBar;
//
//    private SeekBar mAlphaBar;
//
//    private SeekBar mWidthBar;
//
    private int mStrokeColor = Color.BLACK;

    private int mFillColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        // move the camera to Windsor
//      mMap.moveCamera(CameraUpdateFactory.newLatLng(Windsor));
//      mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapLongClickListener(this);

        mFillColor = Color.HSVToColor(10, new float[]{1, 1, 1});

//        DraggableCircle circle = new DraggableCircle(Windsor, DEFAULT_RADIUS);
//        mCircles.add(circle);

        // Move the map so that it is centered on the initial circle
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Windsor, 50f));
    }

    private class DraggableCircle {

        private final Marker centerMarker;

        private final Marker radiusMarker;

        private final Circle circle;

        private double radius;

        public DraggableCircle(LatLng center, double radius) {
            this.radius = radius;
            centerMarker = mMap.addMarker(new MarkerOptions()
                    .position(center)
                    .draggable(true));

            radiusMarker = mMap.addMarker(new MarkerOptions()
                    .position(toRadiusLatLng(center, radius))
                    .draggable(true)
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_AZURE)));

            circle = mMap.addCircle(new CircleOptions()
                    .center(center)
                    .radius(radius)
                    .strokeWidth(10)//modified unsure
                    .strokeColor(mStrokeColor)
                    .fillColor(mFillColor));
        }

        public DraggableCircle(LatLng center, LatLng radiusLatLng) {

            this.radius = toRadiusMeters(center, radiusLatLng);

            centerMarker = mMap.addMarker(new MarkerOptions()
                    .position(center)
                    .draggable(true));

            radiusMarker = mMap.addMarker(new MarkerOptions()
                    .position(radiusLatLng)
                    .draggable(true)
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_AZURE)));

            circle = mMap.addCircle(new CircleOptions()
                    .center(center)
                    .radius(radius)
                    .strokeWidth(5)
                    .strokeColor(mStrokeColor)
                    .fillColor(mFillColor));
        }

        public boolean onMarkerMoved(Marker marker) {
            if (marker.equals(centerMarker)) {
                circle.setCenter(marker.getPosition());
                radiusMarker.setPosition(toRadiusLatLng(marker.getPosition(), radius));
                return true;
            }
            if (marker.equals(radiusMarker)) {
                radius = toRadiusMeters(centerMarker.getPosition(), radiusMarker.getPosition());
                circle.setRadius(radius);
                return true;
            }
            return false;
        }

//        public void onStyleChange() {
//            circle.setStrokeWidth(mWidthBar.getProgress());
//            circle.setFillColor(mFillColor);
//            circle.setStrokeColor(mStrokeColor);
//        }

    }

    /** Generate LatLng of radius marker */
    private static LatLng toRadiusLatLng(LatLng center, double radius) {
        double radiusAngle = Math.toDegrees(radius / RADIUS_OF_EARTH_METERS) /
                Math.cos(Math.toRadians(center.latitude));
        return new LatLng(center.latitude, center.longitude + radiusAngle);
    }

    //calculate the distance between center and radius marker
    private static double toRadiusMeters(LatLng center, LatLng radius) {
        float[] result = new float[1];
        Location.distanceBetween(center.latitude, center.longitude,
                radius.latitude, radius.longitude, result);
        return result[0];
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        onMarkerMoved(marker);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        onMarkerMoved(marker);
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        onMarkerMoved(marker);
    }

    private void onMarkerMoved(Marker marker) {
        for (DraggableCircle draggableCircle : mCircles) {
            if (draggableCircle.onMarkerMoved(marker)) {
                break;
            }
        }
    }

    /*
    不应该存在两个circle! 需要更改！！！！
     */
    @Override
    public void onMapLongClick(LatLng point) {
        // We know the center, let's place the outline at a point 3/4 along the view.
//        View view = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
//                .getView();
//
//        LatLng radiusLatLng = mMap.getProjection().fromScreenLocation(new Point(
//                view.getHeight() * 3 / 4, view.getWidth() * 3 / 4));

        // ok create it
        DraggableCircle circle = new DraggableCircle(point, DEFAULT_RADIUS);

        //test
        for (DraggableCircle draggableCircle : mCircles) {
            draggableCircle.centerMarker.remove();
            draggableCircle.circle.remove();
            draggableCircle.radiusMarker.remove();
        }

        mCircles.clear();

        mCircles.add(circle);
    }

}
