package com.c.dompetabata.Modal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c.dompetabata.Adapter.AdapterProvinsi;
import com.c.dompetabata.Api.Api;
import com.c.dompetabata.Respon.Respon;
import com.c.dompetabata.Helper.RetroClient;
import com.c.dompetabata.Model.ModelProvinsi;
import com.c.dompetabata.R;
import com.c.dompetabata.sharePreference.Preference;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModalProvinsi extends BottomSheetDialogFragment {

    RecyclerView recyclerViewP;
    AdapterProvinsi adapterProvinsi;
    ArrayList<ModelProvinsi> modelProvinsis = new ArrayList<>();
    Button tutup, pilih;
    SearchView searchprovinsi;
    private BottomSheetListener bottomSheetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.modal_layout_provinsi, container, false);
        recyclerViewP = v.findViewById(R.id.ReyProvinsi);

        adapterProvinsi = new AdapterProvinsi(getContext(), modelProvinsis);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewP.setLayoutManager(mLayoutManager);
        recyclerViewP.setAdapter(adapterProvinsi);

        getProvinsi();

        tutup = v.findViewById(R.id.tutupP);
        pilih = v.findViewById(R.id.pilihP);

        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =  Preference.getID(getContext());
                String name = Preference.getName(getContext());

                bottomSheetListener.onButtonClick(name, id);

                dismiss();
            }
        });

        searchprovinsi = v.findViewById(R.id.search_provinsi);
        searchprovinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchprovinsi.onActionViewExpanded();
            }
        });
        searchprovinsi.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapterProvinsi.getFilter().filter(newText);
                return false;
            }
        });


        return v;


    }

    private void getProvinsi() {

        Api api = RetroClient.getApiServices();
        Call<Respon> call = api.getAllProvinsi();
        call.enqueue(new Callback<Respon>() {
            @Override
            public void onResponse(Call<Respon> call, Response<Respon> response) {
                String code = response.body().getCode();

                modelProvinsis = (ArrayList<ModelProvinsi>) response.body().getData();
                adapterProvinsi = new AdapterProvinsi(getContext(), modelProvinsis);
                recyclerViewP.setAdapter(adapterProvinsi);
            }

            @Override
            public void onFailure(Call<Respon> call, Throwable t) {

            }
        });


    }

    public interface BottomSheetListener {
        void onButtonClick(String name, String id);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            bottomSheetListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement bottomsheet Listener");
        }
    }
}
