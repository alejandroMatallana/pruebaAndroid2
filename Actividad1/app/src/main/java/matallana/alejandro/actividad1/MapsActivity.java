package matallana.alejandro.actividad1;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.internal.ma;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener  {

    private GoogleMap mMap;
    ArrayList<LatLng>posiciones;
    ArrayList<PolylineOptions>lineas;
    int marcador =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        lineas= new ArrayList<>();
        posiciones = new ArrayList<>();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setCompassEnabled(true);

        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        //colores variable que se le manda a los marcadores para estos cambien de color

        float colores = (float) (Math.random() * 256) + 0;
        marcador = marcador + 1;

        mMap.addMarker(new MarkerOptions().position(latLng).title("Marcador N° " + marcador ).snippet("Latitud: "+ latLng.latitude + " " + "Longitud: "+latLng.longitude)
                .icon(BitmapDescriptorFactory.defaultMarker(colores))
                .anchor(0.5f,0.5f));

        dibujarLineas(getCurrentFocus(),latLng);
    }

    /*
        Metodo para dibujar una linea entre los marcadores que se van colocando en el mapa
     */
    public void dibujarLineas(View view, LatLng latLng){
        posiciones.add(latLng);

        for(int i=0;i<posiciones.size()-1;i++){
            int R = (int)(Math.random()*256)+0;
            int G = (int)(Math.random()*256)+0;
            int B= (int)(Math.random()*256)+0;

                PolylineOptions line = new PolylineOptions()
                        .add(posiciones.get(i)).add(posiciones.get(i+1)).width(7).color(Color.rgb(R,G,B));
                lineas.add(line);
        }

        for (PolylineOptions linea:lineas) {
            mMap.addPolyline(linea);
        }
    }

}

