package com.souttab.budayalampung;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragment;
import com.andreabaccega.formedittextvalidator.EmailValidator;
import com.andreabaccega.formedittextvalidator.PersonFullNameValidator;
import com.andreabaccega.widget.FormEditText;
import com.souttab.budayalampung.util.JSONPars;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA for
 * <p/>
 * User: andi yulistyo
 * Date: 10/25/13
 * Time: 8:00 PM
 * Task: Skripsiku
 * <p/>
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujua belajar
 */
public class FeedbackFragment extends SherlockFragment {

    private FormEditText editTextNama, editTextEmail, editTextKoment;
    private Button button;
    String komen;
    String nama;
    String email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        button = (Button) view.findViewById(R.id.buttonFeedBack);
        editTextEmail = (FormEditText) view.findViewById(R.id.editTextEmail);
        editTextKoment = (FormEditText) view.findViewById(R.id.editTextSaran);;
        editTextNama = (FormEditText) view.findViewById(R.id.editTextNama);

        clearEditText();

        editTextEmail.addValidator(new EmailValidator(null));
        editTextNama.addValidator(new PersonFullNameValidator(null));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormEditText[] field = {editTextKoment, editTextNama, editTextEmail};

                boolean allValid = true;
                for (FormEditText formEditText : field) {
                    allValid = formEditText.testValidity() && allValid;
                }

                if (allValid) {

                    nama = editTextNama.getText().toString();
                    email = editTextEmail.getText().toString();
                    komen = editTextKoment.getText().toString();

                    new SendToServer().execute();
                    clearEditText();
                }
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getSherlockActivity().getSupportActionBar().setTitle("Kritik dan Saran");
    }

    @Override
    public void onResume() {
        super.onResume();
        clearEditText();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearEditText();
    }

    public void clearEditText(){
        editTextEmail.setText("");
        editTextKoment.setText("");
        editTextNama.setText("");
    }

    @Override
    public void onDetach() {
        super.onDetach();    //To change body of overridden methods use File | Settings | File Templates.
        clearEditText();
    }

    private class SendToServer extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("nama", nama));
            nameValuePairs.add(new BasicNameValuePair("email", email));
            nameValuePairs.add(new BasicNameValuePair("komen", komen));

            JSONObject jsonObject = JSONPars.makeHttpRequest("http://budayalampung.hol.es/.api/feedback.php", "POST", nameValuePairs);

            Log.e("cek Log", jsonObject.toString());

            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getActivity().getApplicationContext(), "Berhasiil di kirim", Toast.LENGTH_SHORT).show();
        }
    }
}