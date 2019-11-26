import React, { PureComponent } from 'react';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, Brush,
  AreaChart, Area,
} from 'recharts';
import axios from 'axios';
import BarLoader from 'react-spinners/BarLoader';


export default class TwoMovingAveragesLineGraph extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      data:[],
      apiLoad: 0
    };
  }

  render() {
    return (

      <div>
        {this.state.apiLoad === 1 ? 

          <AreaChart
            width={500}
            height={200}
            data={this.state.data}
            syncId="anyId"
            margin={{
              top: 10, right: 30, left: 0, bottom: 0,
            }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="name" />
            <YAxis />
            <Tooltip />
            <Area type="monotone" dataKey="P/L" stroke="#d32f2f" fill="#ef5350" />
          </AreaChart>

      : <BarLoader color='white'/>
        }
      </div>
    );
  }

  componentWillMount() {
    axios.get(process.env.REACT_APP_REST_API + '/viewAll/getTMAGraph')
    .then(response => {
      this.setState({
        data: response.data,
        apiLoad: 1
      });
    })
    .catch(error => {
      console.log(error);
    });
  }
}
