// Generated by view binder compiler. Do not edit!
package com.honley.flazemobile.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.honley.flazemobile.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddArticleBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView articleDescriptionImage;

  @NonNull
  public final ImageView articleImage;

  @NonNull
  public final ImageView articleNameImage;

  @NonNull
  public final LinearLayout articleNameLin;

  @NonNull
  public final ImageView articleTextImage;

  @NonNull
  public final LinearLayout articleTextLin;

  @NonNull
  public final Button createArticleBtn;

  @NonNull
  public final LinearLayout descriptionLin;

  @NonNull
  public final EditText inputArticleDescription;

  @NonNull
  public final EditText inputArticleName;

  @NonNull
  public final EditText inputArticleText;

  private ActivityAddArticleBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView articleDescriptionImage, @NonNull ImageView articleImage,
      @NonNull ImageView articleNameImage, @NonNull LinearLayout articleNameLin,
      @NonNull ImageView articleTextImage, @NonNull LinearLayout articleTextLin,
      @NonNull Button createArticleBtn, @NonNull LinearLayout descriptionLin,
      @NonNull EditText inputArticleDescription, @NonNull EditText inputArticleName,
      @NonNull EditText inputArticleText) {
    this.rootView = rootView;
    this.articleDescriptionImage = articleDescriptionImage;
    this.articleImage = articleImage;
    this.articleNameImage = articleNameImage;
    this.articleNameLin = articleNameLin;
    this.articleTextImage = articleTextImage;
    this.articleTextLin = articleTextLin;
    this.createArticleBtn = createArticleBtn;
    this.descriptionLin = descriptionLin;
    this.inputArticleDescription = inputArticleDescription;
    this.inputArticleName = inputArticleName;
    this.inputArticleText = inputArticleText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddArticleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddArticleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_article, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddArticleBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.article_description_image;
      ImageView articleDescriptionImage = ViewBindings.findChildViewById(rootView, id);
      if (articleDescriptionImage == null) {
        break missingId;
      }

      id = R.id.article_image;
      ImageView articleImage = ViewBindings.findChildViewById(rootView, id);
      if (articleImage == null) {
        break missingId;
      }

      id = R.id.article_name_image;
      ImageView articleNameImage = ViewBindings.findChildViewById(rootView, id);
      if (articleNameImage == null) {
        break missingId;
      }

      id = R.id.article_name_lin;
      LinearLayout articleNameLin = ViewBindings.findChildViewById(rootView, id);
      if (articleNameLin == null) {
        break missingId;
      }

      id = R.id.article_text_image;
      ImageView articleTextImage = ViewBindings.findChildViewById(rootView, id);
      if (articleTextImage == null) {
        break missingId;
      }

      id = R.id.article_text_lin;
      LinearLayout articleTextLin = ViewBindings.findChildViewById(rootView, id);
      if (articleTextLin == null) {
        break missingId;
      }

      id = R.id.create_article_btn;
      Button createArticleBtn = ViewBindings.findChildViewById(rootView, id);
      if (createArticleBtn == null) {
        break missingId;
      }

      id = R.id.description_lin;
      LinearLayout descriptionLin = ViewBindings.findChildViewById(rootView, id);
      if (descriptionLin == null) {
        break missingId;
      }

      id = R.id.input_article_description;
      EditText inputArticleDescription = ViewBindings.findChildViewById(rootView, id);
      if (inputArticleDescription == null) {
        break missingId;
      }

      id = R.id.input_article_name;
      EditText inputArticleName = ViewBindings.findChildViewById(rootView, id);
      if (inputArticleName == null) {
        break missingId;
      }

      id = R.id.input_article_text;
      EditText inputArticleText = ViewBindings.findChildViewById(rootView, id);
      if (inputArticleText == null) {
        break missingId;
      }

      return new ActivityAddArticleBinding((ConstraintLayout) rootView, articleDescriptionImage,
          articleImage, articleNameImage, articleNameLin, articleTextImage, articleTextLin,
          createArticleBtn, descriptionLin, inputArticleDescription, inputArticleName,
          inputArticleText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
