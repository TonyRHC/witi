import React, { PureComponent } from 'react';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, Brush,
  AreaChart, Area,
} from 'recharts';

const data = [
  {
    name: '18:00:01', 'P/L': 4000, pv: 2400, amt: 2400,
  },
  {
    name: '18:00:02', 'P/L': 3000, pv: 1398, amt: 2210,
  },
  {
    name: '18:00:03', 'P/L': 2000, pv: 9800, amt: 2290,
  },
  {
    name: '18:00:04', 'P/L': 2780, pv: 3908, amt: 2000,
  },
  {
    name: '18:00:05', 'P/L': 1890, pv: 4800, amt: 2181,
  },
  {
    name: '18:00:06', 'P/L': 2390, pv: 3800, amt: 2500,
  },
  {
    name: '18:00:07', 'P/L': 3490, pv: 4300, amt: 2100,
  },
];

export default class BollingerBandsLineGraph extends PureComponent {
  static jsfiddleUrl = 'https://jsfiddle.net/alidingling/nskpgcrz/';

  render() {
    return (
      <AreaChart
        width={500}
        height={200}
        data={data}
        syncId="anyId"
        margin={{
          top: 10, right: 30, left: 0, bottom: 0,
        }}
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="name" />
        <YAxis />
        <Tooltip />
        <Area type="monotone" dataKey="P/L" stroke="#ffa000" fill="#ffca28" />
      </AreaChart>
    );
  }
}
