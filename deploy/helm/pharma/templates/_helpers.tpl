{{- define "pharmaflow.name" -}}
{{- .Chart.Name -}}
{{- end -}}

{{- define "pharmaflow.fullname" -}}
{{- printf "%s-%s" .Release.Name .Chart.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}