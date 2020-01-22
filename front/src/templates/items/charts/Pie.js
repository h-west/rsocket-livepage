import { mixins, Pie } from 'vue-chartjs'

export default {
    extends: Pie,
    mixins: [mixins.reactiveProp],
    props: ['chartData','options'],
    mounted() {
        this.renderChart(this.chartData, this.options)
    },
    methods: {
        update () {
            this.$data._chart.update()
        }
    }
}