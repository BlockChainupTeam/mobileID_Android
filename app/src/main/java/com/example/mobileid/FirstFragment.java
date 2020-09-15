package com.example.mobileid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klaytn.caver.*;
import com.klaytn.caver.contract.Contract;
import com.klaytn.caver.wallet.keyring.AbstractKeyring;
import com.klaytn.caver.wallet.keyring.KeyStore;
import com.klaytn.caver.wallet.keyring.KeyringFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.ObjectMapperFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txt = (TextView) view.findViewById(R.id.textview_first);
        Caver caver = new Caver(Caver.BAOBAB_URL);
        String ABIJson = "";
        String Address = "";
        try {
            InputStream in = getResources().openRawResource(R.raw.abi);
            File file = File.createTempFile("abi", "tmp");
            IOUtils.copy(in,new FileOutputStream(file));
            ABIJson = FileUtils.readFileToString(file);
            //ABI File -> String
            in = getResources().openRawResource(R.raw.address);
            IOUtils.copy(in,new FileOutputStream(file));
            Address = FileUtils.readFileToString(file);
            //Address File -> String
        } catch (IOException e) {
            e.printStackTrace();
        }
        Contract contract = null;
        try {
            //Read keystore json file.
            File file = new File("/data/data/com.example.mobileid/keystore.json");
            //Decrypt keystore.
            ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
            KeyStore keyStore = objectMapper.readValue(file, KeyStore.class);
            AbstractKeyring keyring = KeyringFactory.decrypt(keyStore, "password");
            //Add to caver wallet.
            caver.wallet.add(keyring);
            contract = new Contract(caver,ABIJson,Address);
            txt.setText("KeyStore Authorized & Contract Load : "+contract.getContractAddress());
        } catch (IOException | CipherException e) {
            txt.setText("Fail: "+e);
        }
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}