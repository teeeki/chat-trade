INSERT INTO users(username, password)
VALUES('t-teieki', 'himitu');
INSERT INTO users(username, password)
VALUES('aaa', 'ccc');
INSERT INTO users(username, password)
VALUES('bbb', 'bbb');
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
        '教科書A',
        500,
        '基礎から学ぶ○○',
        '日常会話や基本的な文法を中心に、リスニングやスピーキングの練習を交えながら、実践的な英語力を養う。',
        '/img/やさしいjava.png'
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
        '教科書b',
        1000,
        '基礎から学ぶ○○',
        '日常会話や基本的な文法を中心に、リスニングやスピーキングの練習を交えながら、実践的な英語力を養う。',
        '/img/オライリーML.png'
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
        '基礎から学ぶ○○',
        '日常会話や基本的な文法を中心に、リスニングやスピーキングの練習を交えながら、実践的な英語力を養う。',
        '/img/マスタリングTCPIP.png'
    );