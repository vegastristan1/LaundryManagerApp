package com.example.laundryshopmanagercapstone.ui.productList;

import android.content.DialogInterface;
import android.media.tv.TvContract;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.laundryshopmanagercapstone.R;
import com.example.laundryshopmanagercapstone.apiconnection.Api;
import com.example.laundryshopmanagercapstone.databinding.FragmentProductListBinding;

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


public class ProductListFragment extends Fragment {
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    ProgressBar progressBar;
    ListView listView;

    List<com.example.laundryshopmanagercapstone.apiconnection.Service> serviceList;

    private ProductListViewModel productListViewModel;
    private FragmentProductListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*productListViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);

        binding = FragmentProductListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textProductList;
        final ListView listView = binding.listOfProductItem;

        productListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;*/

        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        listView = (ListView) view.findViewById(R.id.listOfProductItem);

        serviceList = new ArrayList<>();
        //readHeroes();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /*private void createHero() {
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
        params.put("name", name);
        params.put("realname", realname);
        params.put("rating", String.valueOf(rating));
        params.put("teamaffiliation", team);

        com.example.laundryappcapstone.MainActivity.PerformNetworkRequest request = new com.example.laundryappcapstone.MainActivity.PerformNetworkRequest(com.example.laundryappcapstone.heroapiconnection.Api.URL_CREATE_HERO, params, CODE_POST_REQUEST);
        request.execute();
    }*/

    private void readServices() {
        com.example.laundryshopmanagercapstone.ui.productList.ProductListFragment.PerformNetworkRequest request = new com.example.laundryshopmanagercapstone.ui.productList.ProductListFragment.PerformNetworkRequest(Api.URL_READ_SERVICE, null, CODE_GET_REQUEST);
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


        com.example.laundryappcapstone.MainActivity.PerformNetworkRequest request = new com.example.laundryappcapstone.MainActivity.PerformNetworkRequest(com.example.laundryappcapstone.heroapiconnection.Api.URL_UPDATE_HERO, params, CODE_POST_REQUEST);
        request.execute();

        buttonAddUpdate.setText("Add");

        editTextName.setText("");
        editTextRealname.setText("");
        ratingBar.setRating(0);
        spinnerTeam.setSelection(0);

        isUpdating = false;
    }

    private void deleteHero(int id) {
        com.example.laundryappcapstone.MainActivity.PerformNetworkRequest request = new com.example.laundryappcapstone.MainActivity.PerformNetworkRequest(com.example.laundryappcapstone.heroapiconnection.Api.URL_DELETE_HERO + id, null, CODE_GET_REQUEST);
        request.execute();
    }*/

    private void refreshHeroList(JSONArray services) throws JSONException {
        serviceList.clear();

        for (int i = 0; i < services.length(); i++) {
            JSONObject obj = services.getJSONObject(i);

            serviceList.add(new com.example.laundryshopmanagercapstone.apiconnection.Service(
                    obj.getInt("id"),
                    obj.getString("service_name"),
                    obj.getString("service_price"),
                    obj.getString("service_desc"),
                    obj.getString("teamaffiliation"),
                    obj.getString("rating"),
                    obj.getString("teamaffiliation")
            ));
        }

        com.example.laundryshopmanagercapstone.ui.productList.ProductListFragment.HeroAdapter adapter = new com.example.laundryshopmanagercapstone.ui.productList.ProductListFragment.HeroAdapter(serviceList);
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
            //progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
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
            super(listView.getContext(), R.layout.layout_manager_list, heroList);
            this.heroList = heroList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_manager_list, null, true);

            TextView textViewName = listViewItem.findViewById(R.id.textViewName);

            final com.example.laundryshopmanagercapstone.apiconnection.Hero hero = heroList.get(position);

            textViewName.setText(hero.getName());

            return listViewItem;
        }
    }

}