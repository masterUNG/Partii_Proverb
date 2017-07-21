package th.or.nectec.partii.proverb.partii_proverb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import th.or.nectec.partii.embedded.android.EmbeddedUtils.ModelUtil;
import th.or.nectec.partii.embedded.android.RecognitionListener;
import th.or.nectec.partii.embedded.android.SpeechRecognizer;

public class MainActivity extends AppCompatActivity implements RecognitionListener, ModelUtil.OnReceiveStatusListener {
    int record_state = 0; // 0 non record, 1 record
    int picorder = 1;

    private SpeechRecognizer recognizer;

    private boolean isSetupRecognizer = false;
    private ModelUtil mUtil = new ModelUtil();

    TextView asranswertxt;
    ImageView imgview;
    ImageView answerstatus;
    FloatingActionButton next;
    FloatingActionButton back;
    String answer = "";

    String apikey = "2go-9W7FnLAX3M";


    public void changeimg(ImageView imgview) {

        if (picorder == 1) {
            imgview.setImageResource(R.drawable.i01);
            answer = getResources().getString(R.string.i01);
        } else if (picorder == 2) {
            imgview.setImageResource(R.drawable.i02);
            answer =  getResources().getString(R.string.i02);
        } else if (picorder == 3) {
            imgview.setImageResource(R.drawable.i03);
            answer =  getResources().getString(R.string.i03);
        } else if (picorder == 4) {
            imgview.setImageResource(R.drawable.i04);
            answer =  getResources().getString(R.string.i04);
        } else if (picorder == 5) {
            imgview.setImageResource(R.drawable.i05);
            answer =  getResources().getString(R.string.i05);
        } else if (picorder == 6) {
            imgview.setImageResource(R.drawable.i06);
            answer =  getResources().getString(R.string.i06);
        } else if (picorder == 7) {
            imgview.setImageResource(R.drawable.i07);
            answer =  getResources().getString(R.string.i07);
        } else if (picorder == 8) {
            imgview.setImageResource(R.drawable.i08);
            answer =  getResources().getString(R.string.i08);
        } else if (picorder == 9) {
            imgview.setImageResource(R.drawable.i09);
            answer =  getResources().getString(R.string.i09);
        } else if (picorder == 10) {
            imgview.setImageResource(R.drawable.i10);
            answer =  getResources().getString(R.string.i10);
        } else if (picorder == 11) {
            imgview.setImageResource(R.drawable.i11);
            answer =  getResources().getString(R.string.i11);
        } else if (picorder == 12) {
            imgview.setImageResource(R.drawable.i12);
            answer =  getResources().getString(R.string.i12);
        } else if (picorder == 13) {
            imgview.setImageResource(R.drawable.i13);
            answer =  getResources().getString(R.string.i13);
        } else if (picorder == 14) {
            imgview.setImageResource(R.drawable.i14);
            answer =  getResources().getString(R.string.i14);
        } else if (picorder == 15) {
            imgview.setImageResource(R.drawable.i15);
            answer =  getResources().getString(R.string.i15);
        } else if (picorder == 16) {
            imgview.setImageResource(R.drawable.i16);
            answer =  getResources().getString(R.string.i16);
        } else if (picorder == 17) {
            imgview.setImageResource(R.drawable.i17);
            answer =  getResources().getString(R.string.i17);
        } else if (picorder == 18) {
            imgview.setImageResource(R.drawable.i18);
            answer =  getResources().getString(R.string.i18);
        } else if (picorder == 19) {
            imgview.setImageResource(R.drawable.i19);
            answer =  getResources().getString(R.string.i19);
        } else if (picorder == 20) {
            imgview.setImageResource(R.drawable.i20);
            answer =  getResources().getString(R.string.i20);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        imgview = (ImageView) findViewById(R.id.imageView);
        answerstatus = (ImageView) findViewById(R.id.answerstatus);
        asranswertxt = (TextView) findViewById(R.id.asranswer);

        changeimg(imgview);

        if (mUtil.isPermissionGranted(getApplicationContext())) {
            if (mUtil.isSyncDir(getExternalFilesDir("")) && !isSetupRecognizer) {
                setUpRecognizer();
            }
        } else {
            mUtil.requestPermission(getApplicationContext());
        }

        next = (FloatingActionButton) findViewById(R.id.next);
        back = (FloatingActionButton) findViewById(R.id.back);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUtil.isSyncDir(getExternalFilesDir("")) && !isSetupRecognizer) {
                    setUpRecognizer();
                    Snackbar.make(view, "ลองใหม่อีกครั้ง ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{
                    if (record_state == 0) {
                        record_state = 1;
                        if (isSetupRecognizer) {
                            answerstatus.setVisibility(View.INVISIBLE);
                            asranswertxt.setText("");
                            Log.d("Recognizer", "start recognizer");
                            recognizer.startListening();
                            fab.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), android.R.drawable.presence_audio_busy));
                        }
                    } else if (record_state == 1) {
                        record_state = 0;
                        if (isSetupRecognizer) {
                            Log.d("Recognizer", "stop recognizer");
                            recognizer.stop();
                            fab.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), android.R.drawable.presence_audio_online));
                        }
                    }
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picorder += 1;
                if (picorder > 20) {
                    picorder = 1;
                }
                answerstatus.setVisibility(View.INVISIBLE);
                asranswertxt.setText("");
                changeimg(imgview);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picorder -= 1;
                if (picorder < 1) {
                    picorder = 20;
                }
                answerstatus.setVisibility(View.INVISIBLE);
                asranswertxt.setText("");
                changeimg(imgview);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d("Download", "Start download");
            mUtil.startDownload(this, MainActivity.this, getExternalFilesDir(""), apikey);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUpRecognizer() {
        Log.d("Recognizer", "Setting recognizer");


        recognizer = mUtil.getRecognizer(this);
        if (recognizer.getDecoder() == null) {
            finish();
        }
        recognizer.addListener(this);
        isSetupRecognizer = true;


    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onProgress(int i) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onPartialResult(String s) {

    }

    @Override
    public void onResult(String s) {
        if (s != null) {
            if (!s.equals(SpeechRecognizer.NO_HYP) &&
                    !s.equals(SpeechRecognizer.REQUEST_NEXT)) {

                asranswertxt.setText(s);
                System.out.println("onResult:" + s);

                if(s.trim().equals(answer)){
                    answerstatus.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), android.R.drawable.checkbox_on_background));
                }else{
                    answerstatus.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), android.R.drawable.ic_delete));
                }
                answerstatus.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onTimeout() {

    }

    @Override
    public void onReceiveDownloadComplete() {
        recognizer.stop();
        recognizer.shutdown();
        setUpRecognizer();
    }

    @Override
    public void onReceiveDownloadFailed() {

    }
}
