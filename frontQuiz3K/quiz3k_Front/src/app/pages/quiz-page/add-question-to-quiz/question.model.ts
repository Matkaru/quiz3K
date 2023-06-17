export interface Question {
  id: number;
  questionText: string;
  // answers: Answer[];
  // correctAnswerId: string;
  questionType: string;
  questionQuizId: string;
  answers?: Answer[];
}

export interface Answer {
  id: number;
  answerForTheQuestion: string;
  confirmedAnswer: boolean;
  // deleted?: boolean;
  answerQuestionId: number;

}
