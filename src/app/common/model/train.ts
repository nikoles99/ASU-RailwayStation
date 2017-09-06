import {Schedule} from "./schedule";
import {Carriage} from "./cariage";

export class Train{
  name: string;
  carriages: Carriage[];
  schedules: Schedule[];
}
