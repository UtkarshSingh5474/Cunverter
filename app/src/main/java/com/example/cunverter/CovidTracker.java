package com.example.cunverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cunverter.databinding.ActivityCovidTrackerBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class CovidTracker extends AppCompatActivity {

    private final String TAG = "CovidTracker";

    private ActivityCovidTrackerBinding binding;

    TextView activeCount, confirmedCount, recoveredCount, deceasedCount, vacc1Count, vacc2Count;
    Spinner stateSpinner, citySpinner;

    int active, confirmed, recovered, deceased, vacc1, vacc2;

    String stateName, stateCodeName, cityName;

    //String[] StateCodes = getResources().getStringArray(R.array.StateCodes);

    private ArrayAdapter<CharSequence> stateAdapter, cityAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCovidTrackerBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        binding.toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData(); // Refreshed
                pullToRefresh.setRefreshing(false);
            }
        });       // Pull To Refresh

        activeCount = binding.activeCount;
        confirmedCount = binding.confirmedCount;
        recoveredCount = binding.recoveredCount;
        deceasedCount = binding.deceasedCount;
        vacc1Count = binding.vacc1Count;
        vacc2Count = binding.vacc2Count;

        stateSpinner = binding.stateSpinner;
        citySpinner = binding.citySpinner;

        stateAdapter = ArrayAdapter.createFromResource(this,R.array.States,R.layout.spinneritem);
        stateAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);

        //stateName = "UP";
        //cityName = "Ghaziabad";

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                citySpinner = findViewById(R.id.citySpinner);
                stateName = stateSpinner.getSelectedItem().toString();

                int parentID = parent.getId();
                if (parentID == R.id.stateSpinner) {
                    switch (stateName) {
                        case "State":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_default_districts, R.layout.spinneritem);
                            break;
                        case "Andhra Pradesh":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_andhra_pradesh_districts, R.layout.spinneritem);
                            break;
                        case "Arunachal Pradesh":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_arunachal_pradesh_districts, R.layout.spinneritem);
                            break;
                        case "Assam":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_assam_districts, R.layout.spinneritem);
                            break;
                        case "Bihar":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_bihar_districts, R.layout.spinneritem);
                            break;
                        case "Chhattisgarh":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_chhattisgarh_districts, R.layout.spinneritem);
                            break;
                        case "Goa":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_goa_districts, R.layout.spinneritem);
                            break;
                        case "Gujarat":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_gujarat_districts, R.layout.spinneritem);
                            break;
                        case "Haryana":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_haryana_districts, R.layout.spinneritem);
                            break;
                        case "Himachal Pradesh":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_himachal_pradesh_districts, R.layout.spinneritem);
                            break;
                        case "Jharkhand":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_jharkhand_districts, R.layout.spinneritem);
                            break;
                        case "Karnataka":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_karnataka_districts, R.layout.spinneritem);
                            break;
                        case "Kerala":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_kerala_districts, R.layout.spinneritem);
                            break;
                        case "Madhya Pradesh":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_madhya_pradesh_districts, R.layout.spinneritem);
                            break;
                        case "Maharashtra":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_maharashtra_districts, R.layout.spinneritem);
                            break;
                        case "Manipur":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_manipur_districts, R.layout.spinneritem);
                            break;
                        case "Meghalaya":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_meghalaya_districts, R.layout.spinneritem);
                            break;
                        case "Mizoram":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_mizoram_districts, R.layout.spinneritem);
                            break;
                        case "Nagaland":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_nagaland_districts, R.layout.spinneritem);
                            break;
                        case "Odisha":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_odisha_districts, R.layout.spinneritem);
                            break;
                        case "Punjab":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_punjab_districts, R.layout.spinneritem);
                            break;
                        case "Rajasthan":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_rajasthan_districts, R.layout.spinneritem);
                            break;
                        case "Sikkim":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_sikkim_districts, R.layout.spinneritem);
                            break;
                        case "Tamil Nadu":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_tamil_nadu_districts, R.layout.spinneritem);
                            break;
                        case "Telangana":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_telangana_districts, R.layout.spinneritem);
                            break;
                        case "Tripura":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_tripura_districts, R.layout.spinneritem);
                            break;
                        case "Uttar Pradesh":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_uttar_pradesh_districts, R.layout.spinneritem);
                            break;
                        case "Uttarakhand":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_uttarakhand_districts, R.layout.spinneritem);
                            break;
                        case "West Bengal":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_west_bengal_districts, R.layout.spinneritem);
                            break;
                        case "Andaman and Nicobar Islands":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_andaman_nicobar_districts, R.layout.spinneritem);
                            break;
                        case "Chandigarh":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_chandigarh_districts, R.layout.spinneritem);
                            break;
                        case "Dadra and Nagar Haveli":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_dadra_nagar_haveli_districts, R.layout.spinneritem);
                            break;
                        case "Daman and Diu":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_daman_diu_districts, R.layout.spinneritem);
                            break;
                        case "Delhi":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_delhi_districts, R.layout.spinneritem);
                            break;
                        case "Jammu and Kashmir":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_jammu_kashmir_districts, R.layout.spinneritem);
                            break;
                        case "Lakshadweep":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_lakshadweep_districts, R.layout.spinneritem);
                            break;
                        case "Ladakh":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_ladakh_districts, R.layout.spinneritem);
                            break;
                        case "Puducherry":
                            cityAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_puducherry_districts, R.layout.spinneritem);
                            break;
                        default:
                            break;
                    }
                    cityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);    // Specify the layout to use when the list of choices appears
                    citySpinner.setAdapter(cityAdapter);

                    //To obtain the selected District from the spinner
                    citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            cityName = citySpinner.getSelectedItem().toString();
                            fetchData();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
                fetchData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }


    private void fetchData() {
        stateCodeName = getResources().getStringArray(R.array.StateCodes)[stateSpinner.getSelectedItemPosition()];

        confirmed =-1;
        recovered=-1;
        deceased=-1;


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://data.covid19india.org/v4/min/data.min.json";

        // Create a String request using Volley Library
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            //Getting Objects
                            JSONObject main = new JSONObject(response.toString());
                            JSONObject state = main.getJSONObject(stateCodeName);
                            JSONObject totals = null;
                            if (!stateName.equals("State") && !cityName.equals("City")){
                                JSONObject district = state.getJSONObject("districts");
                                JSONObject city = district.getJSONObject(cityName);
                                totals = city.getJSONObject("total");
                            }
                            else if (!stateName.equals("State") && cityName.equals("City")){
                                totals = state.getJSONObject("total");
                            }else {
                                Toast.makeText(CovidTracker.this, "Please setect Either State or City", Toast.LENGTH_SHORT).show();
                            }

                            //Getting Objects
                            try {
                                confirmed = totals.getInt("confirmed");
                                confirmedCount.setText("" + confirmed);
                            }catch (JSONException e){
                                confirmedCount.setText("No Data");
                            }
                            try {
                                recovered = totals.getInt("recovered");
                                recoveredCount.setText("" + recovered);
                            }catch (JSONException e){
                                recoveredCount.setText("No Data");
                            }
                            try {
                                deceased = totals.getInt("deceased");
                                deceasedCount.setText("" + deceased);
                            }catch (JSONException e){
                                deceasedCount.setText("No Data");
                            }
                            try {
                                vacc1 = totals.getInt("vaccinated1");
                                vacc1Count.setText("" + vacc1);
                            }catch (JSONException e){
                                vacc1Count.setText("No Data");
                            }
                            try {
                                vacc2 = totals.getInt("vaccinated2");
                                vacc2Count.setText("" + vacc2);
                            }catch (JSONException e){
                                vacc2Count.setText("No Data");
                            }

                            if (active != -1 && confirmed != -1 && recovered != -1 ){
                                active=confirmed-recovered-deceased;
                                activeCount.setText("" + active);
                            }else {
                                activeCount.setText("No Data");
                            }


                        } catch (JSONException e) {

                            Log.e(TAG, "Sorry,No Data Available" + e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Wrong-2");
            }
        });
        requestQueue.add(request);
    }
}












