export interface Question {
  id: string;
  questionText: string;
  answers: Answer[];
  correctAnswerId: string;
  questionType: string;
  questionQuizId: string;
}

export interface Answer {
  id: number;
  text: string;
  correct: boolean;
  deleted?: boolean;

}
