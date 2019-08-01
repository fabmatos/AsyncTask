package com.fabriciomatos.tarefasassincronas;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.progressBar = findViewById(R.id.progressBar);
    }

    public void iniciarAssyncTask(View view){
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(10);
    }

    // Parametros que serão passados para a classe / Void para ser vazio
    // Tipo de valor que será utilzado para o progresso da tarefa
    // Retorno após a tarefa ser finalizada
    class MyAsyncTask extends AsyncTask< Integer, Integer, String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int numero = integers[0];
            for(int i = 0; i <= numero; i++){
                //atualiza a progressBar no metodo onProgressUpdate
                publishProgress(i*10);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
