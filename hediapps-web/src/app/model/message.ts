import {User} from './user';
export class Message {
  public fromUser: User;
  public destination: string;
  public subject: string;
  public delai: string;
  public text: string;
  public read: boolean;
  public creationDate: number;
}
