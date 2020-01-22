<template>
    <div>
        <div>{{name}}</div>
        <div style="width:40%">
            <component :is='chartComponent' v-if="chartComponent" v-bind="chart" ref="chart"></component>
        </div>
        <v-btn @click="random">Random</v-btn>
    </div>
</template>

<script>
let randomScalingFactor = function() {
    return Math.round(Math.random() * 100);
};
export default {
    name: 'opinion',
    props: ['name','chart'],
    computed: {
        chartComponent(){
            return this.chart? () => import(`./charts/${this.chart.type}.js`) : null;
        }
    },
    methods: {
        random() {
            this.chart.chartData.datasets.forEach(function(dataset) {
				dataset.data = dataset.data.map(function() {
					return randomScalingFactor();
				});
            });
            this.$refs.chart.update();
        },
        handle(result) {  // mixin??
            //console.log(result);
            this.random();
        }
    }
}

/*  item object
{
    "type" : "Opinion",
    "name" : "opinion name!",
    "chart" : {
        "type" : "Pie",
        "chartData" : {
            "datasets" : [ 
                {
                    "data" : [ 
                        10, 
                        20, 
                        30
                    ],
                    "backgroundColor" : [ 
                        "red", 
                        "orange", 
                        "yellow"
                    ],
                    "label" : "Dataset 1"
                }
            ],
            "labels" : [ 
                "Red", 
                "Yellow", 
                "Blue"
            ]
        },
        "options" : {
            "responsive" : true
        }
    }
}
 */
</script>