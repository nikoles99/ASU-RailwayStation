import {Schedule} from "./schedule";
import {Carriage} from "./cariage";

export class Train {
  id: number;
  name: string;
  carriages: Carriage[];
  schedules: Schedule[];
}
