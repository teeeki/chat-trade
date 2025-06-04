INSERT INTO users(username, password, is_verify)
VALUES(
        't-teieki',
        'himitu',
        FALSE
    );
INSERT INTO users(username, password, is_verify)
VALUES('aaa', 'ccc', FALSE);
INSERT INTO users(username, password, is_verify)
VALUES('bbb', 'bbb', FALSE);
INSERT INTO items(
        user_id,
        name,
        price,
        abstract,
        description,
        img_path
    )
VALUES(
        1,
        'やさしいjava',
        1500,
        '初心者向けにJavaを基礎から解説した入門書',
        '「やさしいJava」は、プログラミング初心者が無理なくJavaを学べるように構成された入門書です。図解や具体例が豊富で、文法の基本からオブジェクト指向、GUIアプリ作成まで丁寧に解説されています。学校教材としても使われることが多く、初学者に人気があります。',
        '/item_img/やさしいjava.png'
    );
INSERT INTO items(
        user_id,
        name,
        price,
        abstract,
        description,
        img_path
    )
VALUES(
        2,
        'ゼロから作るDeep Learning入門',
        1000,
        '理論と実践的に学べる機械学習の定番書',
        '「オライリー機械学習（Pythonではじめる機械学習）」は、Pythonを使って機械学習の基本概念から実装までを学べる実践的な入門書です。scikit-learnなどの主要ライブラリを使いながら、分類・回帰・クラスタリングなどの手法を段階的に習得できます。直感的で丁寧な解説が特徴です。',
        '/item_img/オライリーML.png'
    );
INSERT INTO items(
        user_id,
        name,
        price,
        abstract,
        description,
        img_path
    )
VALUES(
        3,
        'マスタリングTCPIP',
        3000,
        'TCP/IPの基礎から応用まで学べる定番書',
        '「マスタリングTCP/IP」は、ネットワークの基本プロトコルであるTCP/IPについて、構造や仕組みを体系的に学べる書籍です。IPアドレス、ルーティング、DNS、HTTPなどを豊富な図解とともにわかりやすく解説しており、ネットワーク技術者や資格試験対策にも適しています。',
        '/item_img/マスタリングTCPIP.png'
    );
INSERT INTO items(
        user_id,
        name,
        price,
        abstract,
        description,
        img_path
    )
VALUES(
        3,
        'きのこの山',
        200,
        'めっちゃおいしい',
        'あなたはきのこ派？それともたけのこ派？',
        '/item_img/きのこの山.png'
    );