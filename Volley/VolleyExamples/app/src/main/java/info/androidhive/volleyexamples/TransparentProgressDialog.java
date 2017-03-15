package info.androidhive.volleyexamples;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

/** * Custom Anim Loader: By Amiyo * */
public class TransparentProgressDialog extends Dialog
{

  private ImageView iv;
  public TransparentProgressDialog(Context context)
  {
      super(context, R.style.TransparentProgressDialog);
      WindowManager.LayoutParams wlmp = getWindow().getAttributes();
      wlmp.gravity = Gravity.CENTER_HORIZONTAL;
      getWindow().setAttributes(wlmp);
//        setTitle("Loading...");
      setCancelable(false);
      setOnCancelListener(null);
      LinearLayout layout = new LinearLayout(context);
      layout.setOrientation(LinearLayout.VERTICAL);
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      iv = new ImageView(context);
      iv.setImageResource(R.drawable.spinner);
      layout.addView(iv, params);
      addContentView(layout, params);
  }
  public void setProgress(int progress)
  {
      setTitle(Html.fromHtml("<font color='#54D66A'>" + progress + "%"));
  }
  @Override
  public void show()
  {
      super.show();
      RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
      anim.setInterpolator(new LinearInterpolator());
      anim.setRepeatCount(Animation.INFINITE);
      anim.setDuration(1000);
      iv.setAnimation(anim);
      iv.startAnimation(anim);
  }
}
