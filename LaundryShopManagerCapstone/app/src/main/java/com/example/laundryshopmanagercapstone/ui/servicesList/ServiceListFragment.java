package com.example.laundryshopmanagercapstone.ui.servicesList;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.laundryshopmanagercapstone.R;
import com.example.laundryshopmanagercapstone.apiconnection.Api;
import com.example.laundryshopmanagercapstone.apiconnection.Manager;
import com.example.laundryshopmanagercapstone.databinding.FragmentServiceListBinding;
import com.example.laundryshopmanagercapstone.ui.main.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ServiceListFragment extends Fragment {
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    TextView textViewName, textViewId;
    EditText editTextItemNameType, editTextCategoryNameType, editTextServiceName, editTextServicePrice, editTextServiceDesc, editTextStoreID;

    FloatingActionButton floatingActionButton;
    ProgressBar progressBar;
    ListView listView;
    Spinner spinnerItem, spinnerCategories;

    List<com.example.laundryshopmanagercapstone.apiconnection.Service> serviceList;

    private ServiceListViewModel serviceListViewModel;
    private FragmentServiceListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_service_list, container, false);

        textViewId = view.findViewById(R.id.textViewId);
        textViewName = view.findViewById(R.id.textViewNameOfStore);
        floatingActionButton = view.findViewById(R.id.fab2);
        spinnerItem = view.findViewById(R.id.spinnerItems);

        editTextServiceName = view.findViewById(R.id.editTextAddServiceName);
        editTextServicePrice = view.findViewById(R.id.editTextAddServicePrice);
        editTextServiceDesc = view.findViewById(R.id.editTextAddServiceDesc);

        //getting the current user
        Manager manager = SharedPrefManager.getInstance(getContext()).getUser();

        //setting the values to the textviews
        textViewId.setText(String.valueOf(manager.getId()));
        textViewName.setText(manager.getStore_name());

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


        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        listView = (ListView) view.findViewById(R.id.listOfProductItem);

        serviceList = new ArrayList<>();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupActivity();
            }
        });

        int store_id = Integer.parseInt(textViewId.getText().toString());
        readServiceFromID(store_id);

        //readServices();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void createService() {
        //editTextServiceName give a null and also the editTextAddServiceName
        String servicename = editTextServiceName.getText().toString().trim();
        String serviceprice = editTextServicePrice.getText().toString().trim();
        String servicedesc = editTextServiceDesc.getText().toString().trim();

        String item = spinnerItem.getSelectedItem().toString();
        String category = spinnerCategories.getSelectedItem().toString();
        int store_id = textViewId.getId();

        if (TextUtils.isEmpty(servicename)) {
            editTextServiceName.setError("Please enter service name");
            editTextServiceName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(serviceprice)) {
            editTextServicePrice.setError("Please enter service price");
            editTextServicePrice.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("item_name_type", item);
        params.put("category_name_type", category);
        params.put("service_name", servicename);
        params.put("service_price", serviceprice);
        params.put("service_desc", servicedesc);
        params.put("store_id", String.valueOf(store_id));

        com.example.laundryshopmanagercapstone.ui.servicesList.ServiceListFragment.PerformNetworkRequest request = new com.example.laundryshopmanagercapstone.ui.servicesList.ServiceListFragment.PerformNetworkRequest(Api.URL_CREATE_SERVICE, params, CODE_POST_REQUEST);
        request.execute();
    }

    private void readServiceFromID(int storeId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("store_id", String.valueOf(storeId));

        com.example.laundryshopmanagercapstone.ui.servicesList.ServiceListFragment.PerformNetworkRequest request = new com.example.laundryshopmanagercapstone.ui.servicesList.ServiceListFragment.PerformNetworkRequest(Api.URL_READ_SERVICE_TO_ID, params, CODE_POST_REQUEST);
        request.execute();
    }

    /*private void getServiceFrom() {
        //String store_id = textViewId.getText().toString().trim();
        int store_id = textViewId.getId();

        HashMap<String, String> params = new HashMap<>();
        params.put("store_id", String.valueOf(store_id));

        com.example.laundryshopmanagercapstone.ui.servicesList.ServiceListFragment.PerformNetworkRequest request = new com.example.laundryshopmanagercapstone.ui.servicesList.ServiceListFragment.PerformNetworkRequest(Api.URL_READ_SERVICE_TO_ID, params, CODE_POST_REQUEST);
        request.execute();
    }
*/
    private void readServices() {
        com.example.laundryshopmanagercapstone.ui.servicesList.ServiceListFragment.PerformNetworkRequest request = new com.example.laundryshopmanagercapstone.ui.servicesList.ServiceListFragment.PerformNetworkRequest(Api.URL_READ_SERVICE, null, CODE_GET_REQUEST);
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

    private void refreshServiceList(JSONArray services) throws JSONException {
        serviceList.clear();

        for (int i = 0; i < services.length(); i++) {
            JSONObject obj = services.getJSONObject(i);

            serviceList.add(new com.example.laundryshopmanagercapstone.apiconnection.Service(
                    obj.getInt("id"),
                    obj.getString("services_name"),
                    obj.getString("services_price"),
                    obj.getString("services_desc"),
                    obj.getString("item_name_type"),
                    obj.getString("category_name_type"),
                    obj.getString("store_name")
            ));
        }

        com.example.laundryshopmanagercapstone.ui.servicesList.ServiceListFragment.ServiceAdapter adapter = new com.example.laundryshopmanagercapstone.ui.servicesList.ServiceListFragment.ServiceAdapter(serviceList);
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
                    refreshServiceList(object.getJSONArray("services"));
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

    class ServiceAdapter extends ArrayAdapter<com.example.laundryshopmanagercapstone.apiconnection.Service> {
        List<com.example.laundryshopmanagercapstone.apiconnection.Service> serviceList;

        public ServiceAdapter(List<com.example.laundryshopmanagercapstone.apiconnection.Service> serviceList) {
            super(listView.getContext(), R.layout.layout_service_item_list, serviceList);
            this.serviceList = serviceList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_service_item_list, null, true);

            TextView textViewName = listViewItem.findViewById(R.id.textViewServiceName);
            TextView textViewServicesDesc = listViewItem.findViewById(R.id.textViewServiceItemDesc);
            TextView textViewServicePrice = listViewItem.findViewById(R.id.textViewServiceItemCost);


            final com.example.laundryshopmanagercapstone.apiconnection.Service service = serviceList.get(position);

            textViewName.setText(service.getServices_name());
            textViewServicesDesc.setText(service.getServices_desc());
            textViewServicePrice.setText(service.getServices_price());
            //add more here to make a visual of the variable name
            return listViewItem;
        }
    }

    public void popupActivity(){
        Dialog dialog = new Dialog(getContext());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_service);

        Button dialog_btn = dialog.findViewById(R.id.buttonAddService);
        dialog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createService();
            }
        });
        dialog.show();

        //region DialogBox
        /*// Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(getContext());

        // Set the message show for the Alert time
        builder.setMessage("Do you want to exit ?");

        // Set Alert Title
        builder.setTitle("Alert !");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // When the user click yes button
                                // then app will close
                                getActivity().finish();
                            }
                        });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // If user click no
                                // then dialog box is canceled.
                                dialog.cancel();
                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();*/
        //endregion
    }
}