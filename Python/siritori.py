from typing import List, Tuple
import random
import re

class Siritori:
    def __init__(self, txt_file: str):
        self.col = False
        self.siritori_list = []
        self.old_noun = ""
        with open(txt_file, "r", encoding='UTF-8') as lines:
            # 1行ずつ読み取る
            for line in lines:
                self.siritori_list.append(line.replace("\n",""))
    def __return_char_list(self, char: str) -> List[str]:
        pattern=re.compile(r'^'+char)
        str_match = [s for s in self.siritori_list if re.match(pattern, s)]
        return str_match
    
    def return_noun(self, noun: str) -> Tuple[str, bool]:
      print(self.old_noun)
      # 返答できているか確認する
      if self.old_noun:
        if not self.old_noun[-1] == noun[0]:
          self.col = True
          return self.old_noun[-1]+"から始まっていません\n"+"あなたの負けです", self.col

      # プレイヤーの返答が"ん"で終わっているかを確認する
      if self.__is_finish_nn(noun):
          self.col = True
          return "'ん'で終わっています\nあなたの負けです", self.col

      first_character_list = self.__return_char_list(noun[-1])
      # listをシャッフルする
      random.shuffle(first_character_list)
      # listの先頭を返し、その要素を削除する.また、その要素が"ん"で終わっているかを確認する
      next_noun = first_character_list.pop()
      self.old_noun = next_noun
      if self.__is_finish_nn(next_noun[-1]):
          self.col = True
          return next_noun+"\n"+"あなたの勝ちです", self.col
      return next_noun, self.col

    def __is_finish_nn(self, noun: str) -> bool:
        if noun[-1] == "ん":
            return True
