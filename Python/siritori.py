from typing import List, Tuple
import random
import re
from enum import Enum, unique

@unique
class FlagType(Enum):
    FINISH = True
    CONTINUE = False

class Siritori:
    def __init__(self, txt_file: str):
        self.siritori_list = []
        self.old_noun = "し"
        self.__noun_regrex = re.compile(r'''(
            ^[\u3040-\u309F\ー]+$
        )''', re.VERBOSE)
        with open(txt_file, "r", encoding='UTF-8') as lines:
            # 1行ずつ読み取る
            for line in lines:
                self.siritori_list.append(line.replace("\n",""))
        print("しりとり")
    def __return_nextnoun_list(self, text: str) -> List[str] or FlagType:
        '''
        入力されたテキストの最後の文字から始まる名詞のリストを返す

        Args:
            text (str): テキスト

        Returns:
            List[str]: 入力されたテキストの最後の文字から始まる名詞のリスト
        '''
        pattern=re.compile(r'^'+text[-1])
        str_match = [s for s in self.siritori_list if re.match(pattern, s)]
        if not str_match:
            return FlagType.FINISH.value
        return str_match
    def return_nextnoun(self, noun: str) -> Tuple[str, FlagType]:
        '''
        次の語句を返す

        Args:
            noun (str): プレイヤーが入力した語句

        Returns:
            Tuple[str, bool]: str: 次の語句 or 終了メッセージ, FlagType: 終了か続行
        '''
        # ひらがなで入力されているか確認する
        if not self.__noun_regrex.findall(noun):
            return "ひらがなで入力してください", FlagType.CONTINUE.value

        # 返答できているか確認する 
        # 今回は語尾にーがある場合はエラーとする
        if noun[-1] == "ー":
            return "語尾にーをつけることはできません", FlagType.CONTINUE.value
        else:
            if not self.old_noun[-1] == noun[0]:
                return self.old_noun[-1]+"から始まっていません\n"+"あなたの負けです", FlagType.FINISH.value

        # プレイヤーの返答が"ん"で終わっているかを確認する
        if self.__is_finish_nn(noun):
            self.col = FlagType.FINISH.value
            return "'ん'で終わっています\nあなたの負けです", FlagType.FINISH.value

        first_character_list = self.__return_nextnoun_list(noun)
        # 返す語句があるかどうかを確認する
        if first_character_list is FlagType.FINISH.value:
            self.col = FlagType.FINISH.value
            return "返す語句がありません。\nあなたの勝ちです", FlagType.FINISH.value

        # listをシャッフルする
        random.shuffle(first_character_list)
        # listの先頭を返し、その要素を削除する.また、その要素が"ん"で終わっているかを確認する
        next_noun = first_character_list.pop()
        self.old_noun = next_noun
        if self.__is_finish_nn(next_noun):
            return next_noun+"\n"+"あなたの勝ちです", FlagType.FINISH.value
        return next_noun, FlagType.CONTINUE.value
    def __is_finish_nn(self, noun: str) -> bool:
        if noun[-1] == "ん":
            return True

