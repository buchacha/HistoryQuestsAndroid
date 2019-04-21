package com.example.android.historyquests;


import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public final class TemporaryQuests extends AppCompatActivity {

    /**
     * ID квестов
     */
    public static final String WRONG_QUEST_ID = "-1";
    public static final String NULL_QUEST = "0";
    public static final String ID_UNIVERSITY_QUEST = "512";
    public static final String ID_PALCE_SQUARE_QUEST = "256";
    public static final String ADVERT_QUEST_ID = "777";
    public static final String HINT_QUEST_ID = "999";

    /**
     * номера типов вопросов
     */
    public static final String RADIO_BUTTON_TASK_TYPE = "0";
    public static final String CHECK_BOX_TASK_TYPE = "1";
    public static final String EDIT_TEXT_TASK_TYPE = "2";
    public static final String LINK_VARIANTS_TASK_TYPE = "3";
    public final static String EMPTY_TASK_TYPE = "-1";

    /**
     * номера типов медиа-ресурсов
     */
    public static final String NULL_RESOURCE_TYPE = "-1";
    public static final String VIDEO_TYPE = "0";
    public static final String IMAGE_TYPE = "1";

    /**
     * флаги сообщающие о наличии qr-кода
     */
    public static final boolean QR_TRUE = true;
    public static final boolean QR_FALSE = false;

    /**
     * флаги сообщающие о наличии следования до точки
     */
    public static final boolean ROUTE_TRUE = true;
    public static final boolean ROUTE_FALSE = false;

    /**
     * флаги сообщающие о наличии дополнительной информации
     */
    public static final boolean ADD_INFO_TRUE = true;
    public static final boolean ADD_INFO_FALSE = false;

    /**
     * Пустой текст и id для отсутствующего изображение
     */

    public static final String EMPTY_TEXT = "";
    public static final int EMPTY_IMG = -1;


    /**
     * Информация для составления раундов: Вопросы, Ответы, Текст после ответа
     */
    private static final String[] variants_512_1 = {"Злость", "Пунктуальность", "Замкнутость", "Острота ума", "Интерес", "Прилежность"};
    private static final String answer_512_1 = "2456";
    private static final AfterAnswer afterAnswer_512_1 = new AfterAnswer(
            "Молодец! А давай я тебе расскажу про первых универсантов в России? Универсалами\n" +
                    "называли студентов университета. Сам студент – молодой неутомимый исследователь\n" +
                    "жизни, стремящийся найти применение себе в теоретических науках или в практической\n" +
                    "деятельности – остается таковым на протяжении веков. Будь то средневековая Сорбонна,\n" +
                    "«Лига плюща» или Петербургский университет, студента всегда отличают жизнелюбие и\n" +
                    "амбиции. «Будем веселы, пока мы молоды», – так поется в бессмертном «Gaudeamus». Давай послушаем небольшой отрывок из Студенческого гимна!",
            "Пока не правильно",
            VIDEO_TYPE, "aLUKfU2AOBY", EMPTY_IMG
    );

    private static final String[] questionVariants_512_2 = {"«коллегия иностранных дел»",
            "«мануфактур-коллегия»",
            "«военная коллегия»",
            "«адмиралтейская коллегия»",
            "«юстиц-коллегия»",
            "«коммерц-коллегия»"
    };
    private static final String[] variants_512_2 = {"Сношения России с иностранными государствами",
            "Российская промышленность",
            "Военное управление",
            "Военно-морские дела",
            "Уголовные и гражданские споры",
            "Торговля",
            };

    private static final String answer_512_2 = "123456";

    private static final AfterAnswer afterAnswer_512_2 = new AfterAnswer(
            "Молодец! Ты успешно справился с заданием! Здание 12 коллегий строилось с 1722 года\n" +
                    "Доменико Трезини.",
            "Пока не правильно",
            VIDEO_TYPE, "8uLAZzJmmeg", R.drawable.img512x0_1
    ); //надо подумать как брать картинку (лучше из интернета)

    private static final String additionalInfo_512_2 =
                    "Перед тобой главное здание Санкт-Петербургского государственного университета - Здание 12 коллегий. Свое название оно получило оттого, что когда-то здесь планировалось\n" +
                            "разместить 12 коллегий, основанные Петром I, каждая из которых имела строго свои полномочия.\n" +
                            "В частности среди них были «коллегия иностранных дел», «мануфактур-коллегия»,\n" +
                            "«военная коллегия», «адмиралтейская коллегия», «юстиц-коллегия», «коммерц-коллегия»,\n" +
                            "«берг-коллегия», Штатс-коллегия, Камер-коллегия, Ревизион-коллегия, Вотчинная коллегия, Главный Магистрат.";
    private static final RoundInfo roundInfo_512_2 = new RoundInfo(EMPTY_TEXT, additionalInfo_512_2, VIDEO_TYPE, "8uLAZzJmmeg", EMPTY_IMG);

    /**
     * Раунды для первого квеста
     */
    private static final Round[] rounds_512 = {
            new Round(
                    0, "Памятник универсанту", null, CHECK_BOX_TASK_TYPE,
                    "Университет - прежде всего это универсанты. Как ты думаешь, кто такой универсант? Какими чертами он должен обладать?",
                    variants_512_1.length, variants_512_1, null, answer_512_1, VIDEO_TYPE, "8uLAZzJmmeg", EMPTY_IMG, afterAnswer_512_1,
                    null, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE)
            ,
            new Round(
                    1, "Фасад Санкт-Санкт-Петербургского государственного университета", roundInfo_512_2,
                    LINK_VARIANTS_TASK_TYPE,"Как ты думаешь какие коллегии за какие функции отвечали? Соедини друг с другом коллегии и их функции",
                    variants_512_2.length, variants_512_2, questionVariants_512_2, answer_512_2, VIDEO_TYPE, "8uLAZzJmmeg", EMPTY_IMG, afterAnswer_512_2,
                    null, QR_FALSE, ROUTE_FALSE, ADD_INFO_TRUE)
    };

    /**
     * Инициализация квеста
     */
    public static final Quest questPetersburgUniversity
            = new Quest(ID_UNIVERSITY_QUEST, "Петербургский университет", "01-04-2019", rounds_512.length, rounds_512);

    public static final Quest questPetersburgUniversity2
            = new Quest(ID_UNIVERSITY_QUEST, "Петербургский университет", "20-04-2019", rounds_512.length, rounds_512);
    /**
     * Контейнер ключ-квест и его инициализация
     */
    public static Map<String, Quest> questsHashMap = new HashMap<String, Quest>();


}


