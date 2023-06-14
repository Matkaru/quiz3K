export interface Question {
  id: number;
  quizId: string;
  questionText: string;
  answers: Answer[];
  correctAnswerId: string;
}

export interface Answer {
  id: number;
  text: string;
  correct: boolean;
  deleted?: boolean;

}
