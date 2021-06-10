package com.example.laundryshopmanagercapstone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundryshopmanagercapstone.apiconnection.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;

public class RegistrationActivity extends AppCompatActivity {
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    EditText editTextStoreId, editTextStoreName, editTextStoreOwner, editTextStoreContactNumber, editTextStoreAddress, editTextStorePassword, editTextStoreEmail;
    Button buttonRegister;
    ProgressBar progressBar;
    ListView listView;

    List<com.example.laundryshopmanagercapstone.apiconnection.Hero>managerList;

    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextStoreId = findViewById(R.id.editTextStoreId);
        editTextStoreName = findViewById(R.id.editTextStoreName);
        editTextStoreOwner = findViewById(R.id.editTextStoreOwner);
        editTextStoreContactNumber = findViewById(R.id.editTextStoreContactNumber);
        editTextStoreAddress = findViewById(R.id.editTextStoreAddress);
        editTextStorePassword = findViewById(R.id.editTextStorePassword);
        editTextStoreEmail = findViewById(R.id.editTextStoreEmail);
        buttonRegister = findViewById(R.id.buttonRegister);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listViewManager);

        managerList = new ArrayList<>();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createHero();
            }
        });
        readHeroes();

    }


    private void createHero() {
        String store_name = editTextStoreName.getText().toString().trim();
        String store_owner = editTextStoreOwner.getText().toString().trim();
        String store_contact_number = editTextStoreContactNumber.getText().toString().trim();
        String store_address = editTextStoreAddress.getText().toString().trim();
        String store_password = editTextStorePassword.getText().toString().trim();
        String store_email = editTextStoreEmail.getText().toString().trim();

        if (TextUtils.isEmpty(store_name)) {
            editTextStoreName.setError("Please enter name");
            editTextStoreName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(store_owner)) {
            editTextStoreOwner.setError("Please enter real name");
            editTextStoreOwner.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(store_contact_number)) {
            editTextStoreContactNumber.setError("Please enter name");
            editTextStoreContactNumber.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(store_address)) {
            editTextStoreAddress.setError("Please enter real name");
            editTextStoreAddress.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(store_password)) {
            editTextStorePassword.setError("Please enter name");
            editTextStorePassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(store_email)) {
            editTextStoreEmail.setError("Please enter real name");
            editTextStoreEmail.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("store_name", store_name);
        params.put("store_owner", store_owner);
        params.put("store_contact_number", store_contact_number);
        params.put("store_address", store_address);
        params.put("store_password", store_password);
        params.put("store_email", store_email);

        com.example.laundryshopmanagercapstone.RegistrationActivity.PerformNetworkRequest request = new com.example.laundryshopmanagercapstone.RegistrationActivity.PerformNetworkRequest(Api.URL_CREATE_STORE_MANAGER, params, CODE_POST_REQUEST);
        request.execute();
    }

    /*private void updateHero() {
        String id = editTextHeroId.getText().toString();
        String name = editTextName.getText().toString().trim();
        String realname = editTextRealname.getText().toString().trim();

        int rating = (int) ratingBar.getRating();

        String team = spinnerTeam.getSelectedItem().toString();


        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Please enter name");
            editTextName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(realname)) {
            editTextRealname.setError("Please enter real name");
            editTextRealname.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        params.put("realname", realname);
        params.put("rating", String.valueOf(rating));
        params.put("teamaffiliation", team);


        com.example.laundrymanagerappcapstone.MainActivity.PerformNetworkRequest request = new com.example.laundrymanagerappcapstone.MainActivity.PerformNetworkRequest(com.example.laundrymanagerappcapstone.apiconnection.Api.URL_UPDATE_HERO, params, CODE_POST_REQUEST);
        request.execute();

        buttonAddUpdate.setText("Add");

        editTextName.setText("");
        editTextRealname.setText("");
        ratingBar.setRating(0);
        spinnerTeam.setSelection(0);

        isUpdating = false;
    }

    private void deleteHero(int id) {
        com.example.laundrymanagerappcapstone.MainActivity.PerformNetworkRequest request = new com.example.laundrymanagerappcapstone.MainActivity.PerformNetworkRequest(com.example.laundrymanagerappcapstone.apiconnection.Api.URL_DELETE_HERO + id, null, CODE_GET_REQUEST);
        request.execute();
    }
*/

    private void readHeroes() {
        com.example.laundryshopmanagercapstone.RegistrationActivity.PerformNetworkRequest request = new com.example.laundryshopmanagercapstone.RegistrationActivity.PerformNetworkRequest(Api.URL_READ_HEROES, null, CODE_GET_REQUEST);
        request.execute();
    }

    private void refreshHeroList(JSONArray heroes) throws JSONException {
        managerList.clear();

        for (int i = 0; i < heroes.length(); i++) {
            JSONObject obj = heroes.getJSONObject(i);

            managerList.add(new com.example.laundryshopmanagercapstone.apiconnection.Hero(
                    obj.getInt("id"),
                    obj.getString("name"),
                    obj.getString("realname"),
                    obj.getInt("rating"),
                    obj.getString("teamaffiliation")
            ));
        }

        com.example.laundryshopmanagercapstone.RegistrationActivity.HeroAdapter adapter = new com.example.laundryshopmanagercapstone.RegistrationActivity.HeroAdapter(managerList);
        listView.setAdapter(adapter);
    }

    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
        String url;
        HashMap<String, String> params;
        int requestCode;

        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    refreshHeroList(object.getJSONArray("heroes"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            com.example.laundryshopmanagercapstone.apiconnection.RequestHandler requestHandler = new com.example.laundryshopmanagercapstone.apiconnection.RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }

    class HeroAdapter extends ArrayAdapter<com.example.laundryshopmanagercapstone.apiconnection.Hero> {
        List<com.example.laundryshopmanagercapstone.apiconnection.Hero> heroList;

        public HeroAdapter(List<com.example.laundryshopmanagercapstone.apiconnection.Hero> heroList) {
            super(com.example.laundryshopmanagercapstone.RegistrationActivity.this, R.layout.layout_manager_list, heroList);
            this.heroList = heroList;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_manager_list, null, true);

            TextView textViewName = listViewItem.findViewById(R.id.textViewName);

            TextView textViewUpdate = listViewItem.findViewById(R.id.textViewUpdate);
            TextView textViewDelete = listViewItem.findViewById(R.id.textViewDelete);

            final com.example.laundryshopmanagercapstone.apiconnection.Hero hero = heroList.get(position);

            textViewName.setText(hero.getName());

            textViewUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isUpdating = true;
                    editTextStoreId.setText(String.valueOf(hero.getId()));
                    editTextStoreName.setText(hero.getName());
                    editTextStoreOwner.setText(hero.getRealname());
                    //ratingBar.setRating(hero.getRating());
                    //spinnerTeam.setSelection(((ArrayAdapter<String>) spinnerTeam.getAdapter()).getPosition(hero.getTeamaffiliation()));
                    //buttonAddUpdate.setText("Update");
                }
            });

            textViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(com.example.laundryshopmanagercapstone.RegistrationActivity.this);

                    builder.setTitle("Delete " + hero.getName())
                            .setMessage("Are you sure you want to delete it?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //deleteHero(hero.getId());
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });

            return listViewItem;
        }
    }

    //region noNeed
    //this method is actually fetching the json string
    private void getJSON(final String urlWebService) {
        /*
         * As fetching the json string is a network operation
         * And we cannot perform a network operation in main thread
         * so we need an AsyncTask
         * The constrains defined here are
         * Void -> We are not passing anything
         * Void -> Nothing at progress update as well
         * String -> After completion it should return a string and it will be the json string
         * */
        class GetJSON extends AsyncTask<Void, Void, String> {

            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    //endregion
}