package app.com.xplore.xplorespannabletext;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 *
 * Estudo sobre Textos denominados Spans no android
 *
 * Span e um subconjunto de caracteres dentro de um conjunto
 * que possui um estilo (fonte, cor) diferente do restante
 * do texto.
 *
 * Essa caracteristica so pode ser atingida utilizando
 * as classes SpannableString e SpannableStringBuilder
 * que implementam interface Spannable (no momento desse estudo 15/08/2017, ainda nao sei
 * se todas as classes que implementam Spannable tem essa caracteristica de ter um suntexto
 * com estilo diferente)
 *
 * */

public class MainActivity extends AppCompatActivity {

    private TextView clickableTextView, styleTextView;

    private final int flags [] = {
            Spanned.SPAN_COMPOSING
        ,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        ,Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        ,Spanned.SPAN_INCLUSIVE_INCLUSIVE
        ,Spanned.SPAN_INTERMEDIATE
        ,Spanned.SPAN_MARK_MARK
        ,Spanned.SPAN_MARK_POINT
        ,Spanned.SPAN_PARAGRAPH
        ,Spanned.SPAN_POINT_MARK
        ,Spanned.SPAN_POINT_POINT
        ,Spanned.SPAN_PRIORITY
        ,Spanned.SPAN_PRIORITY_SHIFT
        ,Spanned.SPAN_USER
        ,Spanned.SPAN_USER_SHIFT
    };

    private Spannable spannable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickableTextView = (TextView) findViewById(R.id.clickable_text_view);

        /**
         *
         * {@link LinkMovementMethod} uma especializacao de {@link ScrollingMovementMethod} que implementa
         * {@link android.text.method.MovementMethod} (ler sobre)
         *
         * {@link LinkMovementMethod} fornece suporte a criar uma substring clicavel como link numa string
         * */
        clickableTextView.setMovementMethod(LinkMovementMethod.getInstance());
        clickableTextView.setFocusable(false);
        String text = clickableTextView.getText().toString();
        String textTarget = "World";
        if(text.contains(textTarget)) {
            int beginIdxString = text.indexOf(textTarget);
            if(beginIdxString > 0) {
                //SpannableStringBuilder spannableStringBuilder = makeTextTargetClickable(text, beginIdxStr, textTarget.length());
                //clickableTextView.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE);
                /**
                 *
                 * {@link SpannableString} &
                 * {@link SpannableStringBuilder} implementam a interface
                 * {@link Spannable} que eh a responsavel por permitir adicionar
                 *  e remover textos marcáveis (links parecidos com a tag anchor HTML)
                 *  a textos maiores. Segundo a comentario na interface, Nem todo
                 *  Spannable eh um Texto mutavel, vide a classe SpannableString
                 *
                 *  Lembrete: A interface {@link android.text.Editable} tem o proposito
                 *  de servir para textos que podem possuir substextos selecionaveis {links}
                 *  e que sao editaveis (O oposto de um texto armazenado numa String, que eh imutavel)
                 * */
                spannable = makeTextTargetClickable2(text, beginIdxString, textTarget.length());
                clickableTextView.setText(spannable, TextView.BufferType.SPANNABLE);
            }
        }

        styleTextView = (TextView) findViewById(R.id.style_text_view);
        text = styleTextView.getText().toString();
        textTarget = "BOLD";
        int beginIdxString = text.indexOf(textTarget);
        if(beginIdxString > 0) {
            Spannable spannable = setStyleToSpannable(beginIdxString, text, textTarget);
            spannable.setSpan(
                    getAbsoluteSizeSpan(60)
                    , beginIdxString
                    , beginIdxString + textTarget.length()
                    , flags[0]
            );
            spannable.setSpan(
                    getBackgroundColorSpan(ContextCompat.getColor(this, R.color.light_yellow_d9))
                    , beginIdxString
                    , beginIdxString + textTarget.length()
                    , flags[0]
            );
            styleTextView.setText(spannable, TextView.BufferType.SPANNABLE);
        }
    }

    /**
     * {@link SpannableStringBuilder}
     *  Permite a criação de uma String mutável.
     *  O par {@link android.text.SpannableString} que permite criar Strings Imutavels, e {@link SpannableStringBuilder}
     *  e comparavel ao par String, StringBuilder, onde respectivamente podemos criar
     *  String Imutáveis e mutáveis
     *
     *  Descricao da google: https://developer.android.com/reference/android/text/SpannableStringBuilder.html
     *  This is the class for text whose content and markup can both be changed.
     *
     *  SpannableString
     *  Descricao da google: https://developer.android.com/reference/android/text/SpannableString.html
     *
     *  This is the class for text whose content
     *  is immutable "but to which markup objects can be attached and detached". For mutable text, see
     *
     * */

    private SpannableStringBuilder makeTextTargetClickable(String completeText, int beginIdxStringTarget, int lenClickableString) {
        // criando uma nova string a partir do Texto completo passado por argumento
        // para esse metodo
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(completeText);
        spannableStringBuilder.setSpan(
                // adicionando um objeto ClickableSpan para que haja um elemento selecionavel no meio do texto completo
                getClickableSpan()
            , beginIdxStringTarget
            , beginIdxStringTarget + lenClickableString
            , flags[0]      // indica como o texto clicavel vai se comportar
        );

        return spannableStringBuilder;
    }

    private StyleSpan getBoldStyleSpan() {
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD) {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                ds.setUnderlineText(true);
            }
        };
        return styleSpan;
    }

    private AbsoluteSizeSpan getAbsoluteSizeSpan(int sizeSpan) {
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(sizeSpan) {};
        return absoluteSizeSpan;
    }

    private ForegroundColorSpan getForegroundColorSpan(int color) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color) {};
        return foregroundColorSpan;
    }

    private BackgroundColorSpan getBackgroundColorSpan(int color) {
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(color) {};
        return backgroundColorSpan;
    }

    private ClickableSpan getClickableSpan() {
        /**
         * {@link ClickableSpan}
         * If an object of this type is attached to the text of a TextView with a movement method of LinkMovementMethod,
         * the affected spans of text can be selected. If selected and clicked, the onClick(View) method will be called.
         *
         * Um objeto do tipo {@link ClickableSpan} for inserido numa substring de uma string
         * em um TextView, esse objeto pode ser selecionado
         * */
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.i("TEXT_CLICKABLE", "DO SOMETHING MFUCK");
                if(widget.getId() == R.id.clickable_text_view) {
                    Log.i("TEXT_CLICKABLE", "TARGET");
                    removeSpan(this);
                }
            }
            /**
             * Makes the text underlined and in the link color.
             *
             * @param ds
             */
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
                ds.setDither(true);
                ds.setUnderlineText(true);
            }

            /**
             * Returns "this" for most CharacterStyles, but for CharacterStyles
             * that were generated by {@link #wrap}, returns the underlying
             * CharacterStyle.
             */
            @Override
            public CharacterStyle getUnderlying() {
                return this;
            }
        };
        return clickableSpan;
    }

    private void removeSpan(Object what) {
        spannable.removeSpan(what);
    }

    private void removeSpan(Spannable spannable, Object what) {
        spannable.removeSpan(what);
    }

    private SpannableString makeTextTargetClickable2(String completeText
            , int beginIdxStringTarget, int lenClickableString){
        // criando uma nova string a partir do Texto completo passado por argumento
        // para esse metodo
        SpannableString spannableString = new SpannableString(completeText);
        /**
         * O objeto ClickableSpan me permite mudar a cor do texto clicavel, destacando
         * o texto para o usuario e permite adicionar um listener para a acao de clique.
         * Assim, quando o usuario clicar no texto destacado, posso tomar alguma acao
         * */
        ClickableSpan clickableSpan = getClickableSpan();
        spannableString.setSpan(
                clickableSpan
            , beginIdxStringTarget
            , beginIdxStringTarget+lenClickableString
            , flags[5]
        );
        return spannableString;
    }


    private Spannable setStyleToSpannable(int beginIdxString, String completeText, String targetText) {
        Spannable spannable = new SpannableStringBuilder(completeText);
        spannable.setSpan(
            getBoldStyleSpan()
            , beginIdxString
            , beginIdxString + targetText.length()
            , flags[0]
        );
        return spannable;
    }

    private Spannable setSizeSpannable(int fontSize, int beginIdxString, String completeText, String targetText) {
        Spannable spannable = new SpannableStringBuilder(completeText);
        spannable.setSpan(
                getAbsoluteSizeSpan(fontSize)
                , beginIdxString
                , beginIdxString + targetText.length()
                , flags[0]
        );
        return spannable;
    }
}
