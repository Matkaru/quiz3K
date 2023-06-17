export interface Question {
  id: number;
  questionText: string;
  questionType: string;
  questionQuizId: string;
  answers?: Answer[];
}

export interface Answer {
  id: number;
  answerForTheQuestion: string;
  confirmedAnswer: boolean;
  answerQuestionId: number;

}
