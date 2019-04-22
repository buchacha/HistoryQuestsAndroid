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
     * флаги сообщающие о наличии дополнительной информации
     */
    public static final boolean HINT_TRUE = true;
    public static final boolean HINT_FALSE = false;

    /**
     * флаги сообщающие о наличии рекламного/пустого окна
     */
    public static final boolean ADVERT_TRUE = true;
    public static final boolean ADVERT_FALSE = false;

    /**
     * Пустой текст и id для отсутствующего изображение
     */

    public static final String EMPTY_TEXT = "";
    public static final int EMPTY_IMG = -1;





    /**
     * Информация для составления раундов: Вопросы, Ответы, Текст после ответа
     */


    /**
     * 1) 512x0
     */
    private static final String title_512_0 = "Станция 1 «Фасад СПбГУ»";
    private static final String routeAdditionalInfo_512_0 =
            " Дорогой друг, подойди к памятнику Универсанту! Так тебе будет удобнее проходить задания.";
    private static final RouteInfo routeInfo_512_0 = new RouteInfo(routeAdditionalInfo_512_0, IMAGE_TYPE, EMPTY_TEXT, R.drawable.img512x0_1);

    private static final String question_512_0 = "Университет - прежде всего это универсанты. Как ты думаешь, кто такой универсант? Какими чертами он должен обладать?";
    private static final String[] variants_512_0 = {"Злость", "Пунктуальность", "Замкнутость", "Острота ума", "Интерес", "Прилежность"};
    private static final String answer_512_0 = "2456";

    private static final AfterAnswer afterAnswer_512_0 = new AfterAnswer(
            "Молодец! А давай я тебе расскажу про первых универсантов в России? Универсантами\n" +
                    "называли студентов университета. Сам студент – молодой неутомимый исследователь\n" +
                    "жизни, стремящийся найти применение себе в теоретических науках или в практической\n" +
                    "деятельности – остается таковым на протяжении веков. Будь то средневековая Сорбонна,\n" +
                    "«Лига плюща» или Петербургский университет, студента всегда отличают жизнелюбие и\n" +
                    "амбиции. «Будем веселы, пока мы молоды», – так поется в бессмертном «Gaudeamus». Давай послушаем небольшой отрывок из Студенческого гимна!",
            "Пока не правильно",
            VIDEO_TYPE, "aLUKfU2AOBY", EMPTY_IMG
    );

    /**
     * 1) 512x1
     */

    /*
    Заголовок
     */
    private static final String title_512_1 = "Станция 1 «Фасад СПбГУ»";

    /*
    Маршрут
     */
    private static final String routeAdditionalInfo_512_1 =
            " Взгляни на фасад! Давай поговорим о том, где уже учились первые студенты России? ";
    private static final RouteInfo routeInfo_512_1 = new RouteInfo(routeAdditionalInfo_512_1, IMAGE_TYPE, EMPTY_TEXT, R.drawable.img512x1_1);

    /*
    Дополнитетльная информация
     */
    private static final String additionalInfo_512_1 =
            "Перед тобой главное здание Санкт-Петербургского государственного университета - Здание 12 коллегий. Свое название оно получило оттого, что когда-то здесь планировалось разместить 12 коллегий, основанные Петром I, каждая из которых имела строго свои полномочия. В частности среди них были «коллегия иностранных дел», «мануфактур-коллегия», «военная коллегия», «адмиралтейская коллегия», «юстиц-коллегия», «коммерц-коллегия», «берг-коллегия», Штатс-коллегия, Камер-коллегия, Ревизион-коллегия, Вотчинная коллегия, Главный Магистрат.";

    private static final RoundInfo roundInfo_512_1 = new RoundInfo(EMPTY_TEXT, additionalInfo_512_1, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG);

    /*
    Вопрос
     */
    private static final String question_512_1 = "Как ты думаешь какие коллегии за какие функции отвечали? Установи соответствие";

    private static final String[] questionVariants_512_1 = {"«коллегия иностранных дел»",
            "«мануфактур-коллегия»",
            "«военная коллегия»",
            "«адмиралтейская коллегия»",
            "«юстиц-коллегия»",
            "«коммерц-коллегия»"
    };
    private static final String[] variants_512_1 = {"Сношения России с иностранными государствами",
            "Российская промышленность",
            "Военное управление",
            "Военно-морские дела",
            "Уголовные и гражданские споры",
            "Торговля",
    };

    private static final String answer_512_1 = "123456";

    /*
    После вопроса
     */

    private static final AfterAnswer afterAnswer_512_1 = new AfterAnswer(
            "Молодец! Ты успешно справился с заданием! Здание 12 коллегий строилось с 1722 года Доменико Трезини.",
            "Пока не правильно",
            IMAGE_TYPE, EMPTY_TEXT, R.drawable.img512x1_2
    );

    /**
     * 1) 512x2
     */

    /*
    Заголовок
     */
    private static final String title_512_2 = "Станция 1 «Фасад СПбГУ»";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */
    private static final String additionalInfo_512_2 =
            "Вначале строительство Здания 12 коллегий велось под руководством Доменико Трезини и Теодора Швертфегера, а завершали его Джузеппе Трезини и Михаил Земцов. Первое заседание коллегий в новом здании состоялось в 1732 году. Основное строительство было завершено к середине 1730-х годов. В 1737—1741 годах с западной стороны здания была пристроена двухэтажная галерея. В 1804 году в здании расположился Педагогический институт, а в 1835 году здание было передано Санкт-Петербургскому университету. Главный фасад не претерпевал изменений с XIX века, однако в советское время все-таки приобрел новые элементы. ";

    private static final RoundInfo roundInfo_512_2 = new RoundInfo(EMPTY_TEXT, additionalInfo_512_1, IMAGE_TYPE, EMPTY_TEXT, R.drawable.img512x2_2);

    /*
    Вопрос
     */
    private static final String question_512_2 =
"Рассмотри фотографию главного фасада, которая была сделана еще в XIX веке. Какие ордена сохранились? Отметь верное";

    private static final String[] variants_512_2 = {"1",
            "2",
            "3",
            "4",
            "5"
    };

    private static final String answer_512_2 = "34";

    /*
    После вопроса
     */

    private static final AfterAnswer afterAnswer_512_2 = new AfterAnswer(
            "Верно! Как я уже сказал в 1835 году в этом здании расположился Санкт-Петербургский государственный университет и существует здесь доныне. Давай войдем и посмотрим, как живут студенты!",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x3
     */

    /*
    Заголовок
     */
    private static final String title_512_3 = "Станция 2 \"Актовый зал СПбГУ\"";

    /*
    Маршрут
     */
    private static final String routeAdditionalInfo_512_3 =
"Поднимись по Главной лестнице Санкт-Петербургского государственного университета. На втором этаже располагается много интересного. Справа от тебя находится Актовый зал СПбГУ. Регулярно здесь проходят концерты, конференции и церемонии вручения дипломов универсантам. Если зал открыт, загляни внутрь!";
    private static final RouteInfo routeInfo_512_3 = new RouteInfo(routeAdditionalInfo_512_3, IMAGE_TYPE, EMPTY_TEXT, R.drawable.img512x3_1);

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */
    private static final String question_512_3 =
"Ежегодно в Актовом зале тысячи студентов получают свои долгожданные дипломы об образовании. А знаешь ли ты известных выпускников Университета? Отметь их.";

    private static final String[] variants_512_3 = {"Петр I - первый император России",
            "Менделеев Дмитрий Иванович - русский учёный-энциклопедист",
            "Пушкин Александр Сергеевич - писатель",
            "Ульянов (Ленин) Владимир Ильич - марксист, российский революционер\n",
            "Дуров Павел Валерьевич - основатель социальной сети «ВКонтакте» и «Telegram»\n",
            "Лавров Сергей Викторович - советский и российский дипломат",
    };

    private static final String answer_512_3 = "245";

    /*
    После вопроса
     */

    private static final AfterAnswer afterAnswer_512_3 = new AfterAnswer(
            "Молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x4
     */

    /*
    Заголовок
     */
    private static final String title_512_4 = "Станция 3 \"Коридор 12 коллегий\"\n";

    /*
    Маршрут
     */
    private static final String routeAdditionalInfo_512_4 =
            " Итак, мы оказались в доминанте Университета - в знаменитом коридоре 12-коллегий\".\n" +
                    "Многие студенты считают, что здесь получаются самые красивые и значимые студенческие фото. Встань посередине коридора и сделай свое фото! Пусть останется на память о нашем приключении!\n ";
    private static final RouteInfo routeInfo_512_4 = new RouteInfo(routeAdditionalInfo_512_4, IMAGE_TYPE, EMPTY_TEXT, R.drawable.img512x4_1);

    /*
    Дополнитетльная информация
     */
    private static final String additionalInfo_512_4 =
"Коридор Петровских Коллегий \n" +
        "Бесконечен, гулок и прям. \n" +
        "(Что угодно может случиться, \n" +
        "Но он будет упрямо сниться \n" +
        "Тем, кто нынче проходит там). \n" +
        "\n" +
        "Анна Ахматова \n" +
        "«Поэма без героя»\n" +
        " \n" +
        "Коридор здания Двенадцати коллегий является одной из главных его достопримечательностей и одним из символов Санкт-Петербургского государственного университета.\n";
    private static final RoundInfo roundInfo_512_4 = new RoundInfo(EMPTY_TEXT, additionalInfo_512_4, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG);

    /*
    Вопрос
     */
    private static final String question_512_4 = "Коридор 12-коллегий украшают скульптуры выдающихся людей, а также скульптурные группы.\n" +
            "Установи соответсвие между изображениями и биографическими данными.\n(Используй кнопку подсказка)";

    private static final String[] questionVariants_512_4 = {"1",
            "2",
            "3",
            "4",
            "5",
            "6"
    };
    private static final String[] variants_512_4 = {"Чебышев Пафнутий Львович (1821-1894) математик ",
            "Бутлеров Александр Михайлович (1828-1886) химик-органик",
            " Чернышевский Николай Гаврилович (1828-1889) писатель, публицист ",
            "Сеченов Иван Михайлович (1829-1905) физиолог, психолог ",
            "Докучаев Василий Васильевич (1846-1903) геолог, почвовед ",
            "Лихачев Дмитрий Сергеевич (1906-1999) филолог, литературовед, историк",
    };

    private static final String answer_512_4 = "123456";

    /*
    После вопроса
     */
    private static final AfterAnswer afterAnswer_512_4 = new AfterAnswer(
            "Молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x5
     */

    /*
    Заголовок
     */
    private static final String title_512_5 = "Станция 3 \"Коридор 12 коллегий\"\n";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */
    private static final String question_512_5 = "Коридор 12-коллегий украшают скульптуры выдающихся людей, а также скульптурные группы.\n" +
            "Установи соответсвие между изображениями и биографическими данными.\n(Используй кнопку подсказка)";

    private static final String[] questionVariants_512_5 = {"1",
            "2",
            "3",
            "4",
            "5",
            "6"
    };

    private static final String[] variants_512_5 = {"Бекетов Андрей Николаевич (1825-1902) ботаник",
            "Меншуткин Николай Александрович (1842-1907) химик",
            "Иностранцев Александр Александрович (1843-1919) геолог",
            "Бородин Иван Парфеньевич (1847-1930) ботаник",
            "Боргман Иван Иванович (1849-1914) физик",
            "Кареев Николай Иванович (1850-1931) историк",
    };

    private static final String answer_512_5 = "123456";

    /*
    После вопроса
     */
    private static final AfterAnswer afterAnswer_512_5 = new AfterAnswer(
            "Молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x6
     */

    /*
    Заголовок
     */
    private static final String title_512_6 = "Станция 4 \"Скамейка Ассоциации\"";

    /*
    Маршрут
     */
    private static final String routeAdditionalInfo_512_6 =
            "А теперь, дорогой друг, мы с тобой пойдем исследовать внутреннее устройство университета! Спускайся вниз по лестнице и поворачивай направо и ищи дверь, которая ведет к выходу во двор!";
    private static final RouteInfo routeInfo_512_6 = new RouteInfo(routeAdditionalInfo_512_6, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG);

    /*
    Дополнитетльная информация
     */
    private static final String additionalInfo_512_6 =
            "Наверное ты немного утомился, справа от тебя стоит скамеечка, присядь на нее и осмотрись.\n" +
                    "Кстати! Эта скамейка была подарена Ассоциации выпускников благодарными студентами. В нашем университете существуют многие организации, которые помогают студентам во время их обучения и даже после него. Жми далее, чтобы узнать больше!";

    private static final RoundInfo roundInfo_512_6 = new RoundInfo(EMPTY_TEXT, additionalInfo_512_6, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG);

    /*
    Вопрос
     */

    /*
    После вопроса
     */

    /*
    Реклама или пустой вопрос
     */

    private static final String advertTitle_512_6 = "ЭНДАУМЕНТ-ФОНД СПбГУ";

    private static final String advertInfo_512_6 =
"   ЭНДАУМЕНТ-ФОНД СПбГУ\n" +
        "Фонд управления целевым капиталом «Развитие Санкт-Петербургского государственного университета» (далее – «Фонд») образован сотрудниками СПбГУ в июне 2008 года.\n" +
        "Фонд делает жизнь студентов лучше и реализует несколько программ:\n" +
        "1. Конкурс Start-up СПбГУ, благодаря которому у тебя сейчас есть возможность пройти такой квест. Именно этот конкурс и Фонд помогли нам в создании.\n" +
        "2. Научный парк СПбГУ\n" +
        "3. Закупка мантий для универсантов и покупка автобуса и многое другое.\n" +
        "Если ты хочешь помочь Фонду и пожертвовать любую сумму на его развитие и работу, нажимай кнопку пожертвовать (возможность перейти по ссылке и пожертвовать: ";

    private static final String donateLink_512_6 = "http://fund.spbu.ru/sdelat_vznos.html";

    private static final Advert advert_512_6 = new Advert(advertTitle_512_6, advertInfo_512_6, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG, donateLink_512_6, true);

    /**
     * 1) 512x6
     */

    /*
    Заголовок
     */
    private static final String title_512_7 = "Станция 4 \"Скамейка Ассоциации\"";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */

    /*
    После вопроса
     */

    /*
    Реклама или пустой вопрос
     */

    private static final String advertTitle_512_7 = "АССОЦИАЦИЯ ВЫПУСКНИКОВ";

    private static final String advertInfo_512_7 =
            "   Ассоциация выпускников СПбГУ объединяет всех, кто учился в первом Университете России – независимо от года выпуска и специальности, места проживания и области деятельности. Каждый универсант важен, уникален и ценен для Университета как часть его истории, научного и культурного наследия." +
                    "Миссия Ассоциации выпускников – объединение выпускников в динамичное и заинтересованное сообщество." +
                    " Пожертвовать на развитие Ассоциации:";
    private static final String donateLink_512_7 = "http://alumni.spbu.ru/donate/";

    private static final Advert advert_512_7 = new Advert(advertTitle_512_7, advertInfo_512_7, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG, donateLink_512_7, true);

    /**
     * 1) 512x
     */

    /*
    Заголовок
     */
    private static final String title_512_8 = "Станция 5 \"Монумент ВОВ\"";

    /*
    Маршрут
     */
    private static final String routeAdditionalInfo_512_8 =
            " Ну немного отдохнул и вперед изучать наш университет дальше! \n" +
                    " \n" +
                    "Подойди к монументу, посвященному погибшим преподавателям и студентам за годы Великой отечественной войны.\n" +
                    "\n" +
                    "Наведи на QR-код и появится видеоролик, снятый специально университетом к годовщине празднования Дня Победы\n ";
    private static final RouteInfo routeInfo_512_8 = new RouteInfo(routeAdditionalInfo_512_8, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG);

    /*
    Дополнитетльная информация
     */
    private static final String additionalInfo_512_8 =
"К сожалению, страницы истории Университета содержат не только радостные, но и грустные страницы... Великая отечественная война унесла жизни многих выдающихся и значимых людей, которые когда-то были студентами, аспирантами и преподавателями Университета";
    private static final RoundInfo roundInfo_512_8 = new RoundInfo(EMPTY_TEXT, additionalInfo_512_8, VIDEO_TYPE, "ZR0UWs2pK6k", EMPTY_IMG);

    /*
    Вопрос
     */
    private static final String question_512_8 = "Во время войны людям помогала вера, надежда и, конечно же, любовь. Все они воспеты во многих песнях и фильмах, посвященных Великой Отечественной войне. Послушай отрывки из песен и фильмов и угадай их названия. Ты готов (-а)?";

    private static final String[] variants_512_8 = {"Да",
            "Нет"
    };

    private static final String answer_512_8 = "1";

    /*
    После вопроса
     */

    private static final AfterAnswer afterAnswer_512_8 = new AfterAnswer(
            "Хорошо! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );


    /**
     * 1) 512x9
     */

    /*
    Заголовок
     */
    private static final String title_512_9 = "Станция 5 \"Монумент ВОВ\"\n";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */
    private static final String question_512_9 = "Послушай отрывки из песен и фильмов и угадай их";


    private static final String[] variants_512_9 = {"Журавли",
            "Ах, путь - дорожка, фронтовая",
            "Синий платочек"
    };

    private static final String answer_512_9 = "1";

    /*
    После вопроса
     */
    private static final AfterAnswer afterAnswer_512_9 = new AfterAnswer(
            "Правильно, молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x10
     */

    /*
    Заголовок
     */
    private static final String title_512_10 = "Станция 5 \"Монумент ВОВ\"\n";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */
    private static final String question_512_10 = "Послушай отрывки из песен и фильмов и угадай их";


    private static final String[] variants_512_10 = {"Ах, путь - дорожка, фронтовая",
            "Священная война",
            "День Победы"
    };

    private static final String answer_512_10 = "1";

    /*
    После вопроса
     */
    private static final AfterAnswer afterAnswer_512_10 = new AfterAnswer(
            "Правильно, молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x11
     */

    /*
    Заголовок
     */
    private static final String title_512_11 = "Станция 5 \"Монумент ВОВ\"\n";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */
    private static final String question_512_11 = "Послушай отрывки из песен и фильмов и угадай их";


    private static final String[] variants_512_11 = {"День Победы",
            "Священная война",
            "А зори здесь тихие"
    };

    private static final String answer_512_11 = "1";

    /*
    После вопроса
     */
    private static final AfterAnswer afterAnswer_512_11 = new AfterAnswer(
            "Правильно, молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x12
     */

    /*
    Заголовок
     */
    private static final String title_512_12 = "Станция 5 \"Монумент ВОВ\"\n";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */
    private static final String question_512_12 = "Послушай отрывки из песен и фильмов и угадай их";


    private static final String[] variants_512_12 = {"Священная война",
            "Баллада о солдате",
            "Синий платочек"
    };

    private static final String answer_512_12 = "1";

    /*
    После вопроса
     */
    private static final AfterAnswer afterAnswer_512_12 = new AfterAnswer(
            "Правильно, молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x13
     */

    /*
    Заголовок
     */
    private static final String title_512_13 = "Станция 5 \"Монумент ВОВ\"\n";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */
    private static final String question_512_13 = "Послушай отрывки из песен и фильмов и угадай их";


    private static final String[] variants_512_13 = {"Синий платочек",
            "А зори здесь тихие",
            "Любовь и голуби"
    };

    private static final String answer_512_13 = "1";

    /*
    После вопроса
     */
    private static final AfterAnswer afterAnswer_512_13 = new AfterAnswer(
            "Правильно, молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x14
     */

    /*
    Заголовок
     */
    private static final String title_512_14 = "Станция 6 \"Университеты\"";

    /*
    Маршрут
     */
    private static final String routeAdditionalInfo_512_14 =
            " Давай теперь узнаем какие университеты сотрудничают с СПбГУ.\n" +
                    " \n" +
                    "Найди QR-код перед современной композицией университетам и получи следующее задание!\n";
    private static final RouteInfo routeInfo_512_14 = new RouteInfo(routeAdditionalInfo_512_14, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG);

    /*
    Дополнитетльная информация
     */
    private static final String additionalInfo_512_14 =
            "Этот «Лабиринт», созданный филологическим и восточным факультетами университета совместно с университетами других стран, стал неординарным вкладом в празднование 300-летия Северной Столицы. Это памятник, построенный по классической схеме, известной более 40 тысяч лет, оригинален по своему исполнению, так как камни для «Лабиринта» присланы из крупнейших университетов мира, что символизирует единство науки разных стран.";

    private static final RoundInfo roundInfo_512_14 = new RoundInfo(EMPTY_TEXT, additionalInfo_512_14, IMAGE_TYPE, EMPTY_TEXT, R.drawable.img512x14_1);

    /*
    Вопрос
     */
    private static final String question_512_14 = "Давай проверим твои знания! Тебе нужно будет угадать в какой стране находится ВУЗ, который я показываю. Ты готов?";


    private static final String[] variants_512_14 = {"Да",
            "Нет"
    };

    private static final String answer_512_14 = "1";

    /*
    После вопроса
     */

    private static final AfterAnswer afterAnswer_512_14 = new AfterAnswer(
            "Хорошо! ",
            EMPTY_TEXT, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x15
     */

    /*
    Заголовок
     */
    private static final String title_512_15 = "Станция 6 \"Университеты\"";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */
    private static final String question_512_15 = "Тебе нужно будет угадать в какой стране находится ВУЗ, который я показываю. Кембриджский университет:\n";


    private static final String answer_512_15 = "Англия_x_Великобритания";

    /*
    После вопроса
     */
    private static final AfterAnswer afterAnswer_512_15 = new AfterAnswer(
            "Правильно, молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x16
     */

    /*
    Заголовок
     */
    private static final String title_512_16 = "Станция 6 \"Университеты\"";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */
    private static final String question_512_16 = "Тебе нужно будет угадать в какой стране находится ВУЗ, который я показываю. Кембриджский университет:\n";


    private static final String answer_512_16 = "США_x_Америка_x_Соединенные штаты америки";

    /*
    После вопроса
     */
    private static final AfterAnswer afterAnswer_512_16 = new AfterAnswer(
            "Правильно, молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x17
     */

    /*
    Заголовок
     */
    private static final String title_512_17 = "Станция 6 \"Университеты\"";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */
    private static final String question_512_17 = "Тебе нужно будет угадать в какой стране находится ВУЗ, который я показываю. Кембриджский университет:\n";


    private static final String answer_512_17 = "Швейцария";

    /*
    После вопроса
     */
    private static final AfterAnswer afterAnswer_512_17 = new AfterAnswer(
            "Правильно, молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );

    /**
     * 1) 512x18
     */

    /*
    Заголовок
     */
    private static final String title_512_18 = "Станция 6 \"Университеты\"";

    /*
    Маршрут
     */

    /*
    Дополнитетльная информация
     */

    /*
    Вопрос
     */
    private static final String question_512_18 = "Тебе нужно будет угадать в какой стране находится ВУЗ, который я показываю. Кембриджский университет:\n";


    private static final String answer_512_18 = "Япония";

    /*
    После вопроса
     */
    private static final AfterAnswer afterAnswer_512_18 = new AfterAnswer(
            "Правильно, молодец! ",
            "Пока не правильно",
            NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG
    );
    /**
     * 512 Раунды
     */
    private static final Round[] rounds_512 = {
            new Round(
                    0, title_512_0, null, CHECK_BOX_TASK_TYPE,
                    question_512_0,
                    variants_512_0.length, variants_512_0, null, answer_512_0, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG, afterAnswer_512_0,
                    routeInfo_512_0, null, QR_TRUE, ROUTE_TRUE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    1, title_512_1, roundInfo_512_1,
                    LINK_VARIANTS_TASK_TYPE,question_512_1,
                    variants_512_1.length, variants_512_1, questionVariants_512_1, answer_512_1, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG, afterAnswer_512_1,
                    routeInfo_512_1, null, QR_FALSE, ROUTE_TRUE, ADD_INFO_TRUE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    2, title_512_2, roundInfo_512_2,
                    CHECK_BOX_TASK_TYPE, question_512_2,
                    variants_512_2.length, variants_512_2, null, answer_512_2, IMAGE_TYPE, EMPTY_TEXT, R.drawable.img512x2_3, afterAnswer_512_2,
                    null, null, QR_FALSE, ROUTE_FALSE, ADD_INFO_TRUE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    3, title_512_3, null,
                    CHECK_BOX_TASK_TYPE, question_512_3,
                    variants_512_3.length, variants_512_3, null, answer_512_3, IMAGE_TYPE, EMPTY_TEXT, R.drawable.img512x3_2, afterAnswer_512_3,
                    routeInfo_512_3, null, QR_TRUE, ROUTE_TRUE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    4, title_512_4, roundInfo_512_4,
                    LINK_VARIANTS_TASK_TYPE, question_512_4,
                    variants_512_4.length, variants_512_4, questionVariants_512_4, answer_512_4, IMAGE_TYPE, EMPTY_TEXT, R.drawable.img512x4_2, afterAnswer_512_4,
                    routeInfo_512_4, null, QR_FALSE, ROUTE_TRUE, ADD_INFO_TRUE, HINT_TRUE, ADVERT_FALSE)
            ,
            new Round(
                    5, title_512_5, null,
                    LINK_VARIANTS_TASK_TYPE, question_512_5,
                    variants_512_5.length, variants_512_5, questionVariants_512_5, answer_512_5, IMAGE_TYPE, EMPTY_TEXT, R.drawable.img512x5_1,
                    afterAnswer_512_5, null, null, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE, HINT_TRUE, ADVERT_FALSE)
            ,
            new Round(
                    6, title_512_6, roundInfo_512_6,
                    EMPTY_TASK_TYPE, EMPTY_TEXT,
                    0, null, null, EMPTY_TEXT, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG,
                    null, routeInfo_512_6, advert_512_6, QR_FALSE, ROUTE_TRUE, ADD_INFO_TRUE, HINT_FALSE, ADVERT_TRUE)
            ,
            new Round(
                    7, EMPTY_TEXT, null,
                    EMPTY_TASK_TYPE, EMPTY_TEXT,
                    0, null, null, EMPTY_TEXT, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG,
                    null, null, advert_512_7, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_TRUE)
            ,
            new Round(
                    8, title_512_8, roundInfo_512_8,
                    RADIO_BUTTON_TASK_TYPE, question_512_8,
                    variants_512_8.length, variants_512_8, null, answer_512_8, NULL_RESOURCE_TYPE, EMPTY_TEXT, EMPTY_IMG,
                    afterAnswer_512_8, routeInfo_512_8, null, QR_FALSE, ROUTE_TRUE, ADD_INFO_TRUE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    9, title_512_9, null,
                    RADIO_BUTTON_TASK_TYPE, question_512_9,
                    variants_512_9.length, variants_512_9, null, answer_512_9, VIDEO_TYPE,"XZZHISSfHv4", EMPTY_IMG,
                    afterAnswer_512_9, null, null, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    10, title_512_10, null,
                    RADIO_BUTTON_TASK_TYPE, question_512_10,
                    variants_512_10.length, variants_512_10, null, answer_512_10, VIDEO_TYPE,"dH4KuCZOTF8", EMPTY_IMG,
                    afterAnswer_512_10, null, null, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    11, title_512_11, null,
                    RADIO_BUTTON_TASK_TYPE, question_512_11,
                    variants_512_11.length, variants_512_11, null, answer_512_11, VIDEO_TYPE,"IVwYbP1q2MA", EMPTY_IMG,
                    afterAnswer_512_11, null, null, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    12, title_512_12, null,
                    RADIO_BUTTON_TASK_TYPE, question_512_12,
                    variants_512_12.length, variants_512_12, null, answer_512_12, VIDEO_TYPE,"X_-eI8kRuvw", EMPTY_IMG,
                    afterAnswer_512_12, null, null, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    13, title_512_13, null,
                    RADIO_BUTTON_TASK_TYPE, question_512_13,
                    variants_512_13.length, variants_512_13, null, answer_512_13, VIDEO_TYPE,"VWSlJwP_xaE", EMPTY_IMG,
                    afterAnswer_512_13, null, null, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    14, title_512_14, roundInfo_512_14,
                    RADIO_BUTTON_TASK_TYPE, question_512_14,
                    variants_512_14.length, variants_512_14, null, answer_512_14, NULL_RESOURCE_TYPE, EMPTY_TEXT,EMPTY_IMG,
                    afterAnswer_512_14, routeInfo_512_14, null, QR_TRUE, ROUTE_TRUE, ADD_INFO_TRUE, HINT_TRUE, ADVERT_FALSE)
            ,
            new Round(
                    15, title_512_15, null,
                    EDIT_TEXT_TASK_TYPE, question_512_15,
                    0, null, null, answer_512_15, IMAGE_TYPE,EMPTY_TEXT, R.drawable.img512x15_1,
                    afterAnswer_512_15, null, null, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    16, title_512_16, null,
                    EDIT_TEXT_TASK_TYPE, question_512_16,
                    0, null, null, answer_512_16, IMAGE_TYPE,EMPTY_TEXT, R.drawable.img512x16_1,
                    afterAnswer_512_16, null, null, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    17, title_512_17, null,
                    EDIT_TEXT_TASK_TYPE, question_512_17,
                    0, null, null, answer_512_17, IMAGE_TYPE,EMPTY_TEXT, R.drawable.img512x17_1,
                    afterAnswer_512_17, null, null, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_FALSE)
            ,
            new Round(
                    18, title_512_18, null,
                    EDIT_TEXT_TASK_TYPE, question_512_18,
                    0, null, null, answer_512_18, IMAGE_TYPE,EMPTY_TEXT, R.drawable.img512x18_1,
                    afterAnswer_512_18, null, null, QR_FALSE, ROUTE_FALSE, ADD_INFO_FALSE, HINT_FALSE, ADVERT_FALSE)
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


