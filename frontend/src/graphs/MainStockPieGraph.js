import React, { PureComponent } from 'react';
import {
  PieChart, Pie, Legend, Tooltip, LabelList
} from 'recharts';
import axios from 'axios';
import BarLoader from 'react-spinners/BarLoader';

export default class MainStockPieGraph extends PureComponent {
  static jsfiddleUrl = 'https://jsfiddle.net/alidingling/k9jkog04/';

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

          <PieChart
            width={400}
            height={400}
            margin={{
              top: 0, right: 0, left: 30, bottom: 0,
            }}
          >
            <Pie dataKey="volume" isAnimationActive={false} data={this.state.data} cx={200} cy={200} outerRadius={120} fill="#66bb6a" label>
     {/*         <LabelList dataKey="name" position="inside" /> */}
            </Pie>
            <Tooltip />
          </PieChart>

      : <BarLoader color='white'/>
        }
      </div>
    );
  }

  componentWillMount() {
    axios.get(process.env.REACT_APP_REST_API + '/home/getVolumeStock')
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
