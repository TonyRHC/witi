import React, { PureComponent } from 'react';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend,
} from 'recharts';
import axios from 'axios';
import BarLoader from 'react-spinners/BarLoader';

export default class MainLineGraph extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      data:[],
      apiLoad: 0
    };
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

  render() {
    return (
      <div>
        {this.state.apiLoad === 1 ? 
          <LineChart
            width={550}
            height={500}
            data={this.state.data}
            margin={{
              top: 5, right: 30, left: 0, bottom: 5,
            }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="name" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Line type="monotone" dataKey="P/L" stroke="#29b6f6" />
          </LineChart>
          : <BarLoader color='white'/>
        }
      </div>
    );
  }
}
